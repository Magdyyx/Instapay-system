package instapay;

import instapay.Enums.UserOperation;
import instapay.Modules.Repositories.AccountRepository;
import instapay.Modules.Repositories.InMemoryAccountRepository;
import instapay.Modules.Repositories.InMemoryUserRepository;
import instapay.Modules.Repositories.UserRepository;
import instapay.Modules.Response.Response;
import instapay.Modules.TransferFacility.InstapayTransferFacility;
import instapay.Modules.TransferFacility.MoneyTransferFacility;
import instapay.Modules.User.User;
import instapay.Modules.ViewModels.BillViewModel;
import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.ViewModels.MoneyTransferViewModel;

import java.util.Optional;
import java.util.ResourceBundle;

public class InstapaySystem {
    private User currentlyLoggedInUser;
    private UserRepository users = new InMemoryUserRepository();
    private AccountRepository accounts = new InMemoryAccountRepository();
    private MoneyTransferFacility facility = new InstapayTransferFacility();

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            int choice = Presenter.welcome();

            switch (choice) {
                case 1:
                    User userData = Presenter.registerUser();

                    if (verifyRegistration(userData)) {
                        // Perform actions for successful registration
                        System.out.println("Registration successful!");
                        // Add User to InMemoryUserRepository
                        users.addUser(userData);

                        // Log the user in.
                        currentlyLoggedInUser = userData;

                        // Redirect to home.
                        homeMenu();
                    } else {
                        System.out.println("Registeration cancelled!");
                    }

                    break;
                case 2:
                    userData = Presenter.loginUser();
                    if (verifyLogin(userData)) {
                        currentlyLoggedInUser = users.getUserByUsername(userData.getUsername()).get();
                        System.out.println("Login successfull!\nWelcome " + currentlyLoggedInUser.getUsername());

                        // Redirect to home.
                        homeMenu();
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please Try Again!");
                    break;
            }
        }
    }

    private boolean verifyRegistration(User user) {
        if (users.getUserByUsername(user.getUsername()).isPresent()) {
            System.out.println("Username already exists!");
            return false;
        }

        if (!user.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
            System.out.println("Please use a strong password!");
            return false;
        }

        if (user.getMoneyProvider() == null) {
            System.out.println("Please select a valid money provider!");
            return false;
        }

        Response response = facility.VerifyAccount(
                user.getMoneyProvider(), user.getProviderAccountIdentifier(), user.getPhone());

        if (!response.succeeded()) {
            System.out.println(response.getErrorMessage());
            return false;
        }

        int otp = response.to(Integer.class);

        return true;
    }

    private boolean verifyLogin(User user) {
        Optional<User> foundUser = users.getUserByUsername(user.getUsername());
        if (foundUser.isEmpty()) {
            return false;
        }

        if (!foundUser.get().getPassword().equals(user.getPassword())) {
            return false;
        }

        return true;
    }

    private void homeMenu() {
        while (true) {
            Presenter.showUserInfo(currentlyLoggedInUser);
            UserOperation operation = Presenter.promptUserOperations();
            switch (operation) {
                case BalanceQuery -> balanceQuery();
                case PayBill -> payBill();
                case TransferToInstapay, TransferToBank, TransferToWallet -> transferMoney(operation);
                case Logout -> {
                    // remove currently logged in user
                    currentlyLoggedInUser = null;
                    return;
                }
                default -> {
                }
            }
        }
    }

    private void transferMoney(UserOperation operation) {
        if (operation == UserOperation.TransferToBank && !currentlyLoggedInUser.accountIsBank()) {
            System.out.println("Transfers to bank accounts are only permitted through a bank account.");
            return;
        }

        MoneyTransferViewModel transferInfo;
        Response response;

        if (operation == UserOperation.TransferToInstapay) {
            transferInfo = Presenter.promptForInstapayTransferInfo();

            response = facility.TransferMoneyToInstapay(currentlyLoggedInUser.getProviderAccountIdentifier(),
                    transferInfo.getReceiver(), transferInfo.getAmount());
        } else {
            transferInfo = Presenter.promptForTransferInfo();

            response = facility.TransferMoney(currentlyLoggedInUser.getProviderAccountIdentifier(),
                    transferInfo.getReceiver(), transferInfo.getProvider(), transferInfo.getAmount());
        }

        if (!response.succeeded()) {
            System.out.println(response.getErrorMessage());
            System.out.println("Failed to complete the transaction");
        }

        System.out.println("Transferred successfully. Transaction is complete.");
    }

    private void payBill() {
        BillViewModel billInfo = Presenter.promptForBillInfo();

        Response response = facility.GetBill(billInfo.getBillId(), billInfo.getType());

        if (!response.succeeded()) {
            System.out.println(response.getErrorMessage());
            return;
        }

        UtilityBill bill = response.to(UtilityBill.class);

        boolean payBill = Presenter.promptPayBill(bill);
        if (!payBill) {
            return;
        }

        response = facility.PayBill(currentlyLoggedInUser.getProviderAccountIdentifier(), billInfo.getBillId(),
                billInfo.getType());
        if (!response.succeeded()) {
            System.out.println(response.getErrorMessage());
        }

        System.out.println("Bill payment successful.");
    }

    private void balanceQuery() {
        Response response = facility.InquireBalance(currentlyLoggedInUser.getProviderAccountIdentifier());

        if (!response.succeeded()) {
            System.out.println(response.getErrorMessage());
            return;
        }

        Presenter.displayBalance(response.to(Double.class));
    }
}
