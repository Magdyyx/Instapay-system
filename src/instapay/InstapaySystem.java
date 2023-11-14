package instapay;

import instapay.Modules.Repositories.AccountRepository;
import instapay.Modules.Repositories.InMemoryAccountRepository;
import instapay.Modules.Repositories.InMemoryUserRepository;
import instapay.Modules.Repositories.UserRepository;
import instapay.Modules.TransferFacility.InstapayTransferFacility;
import instapay.Modules.TransferFacility.MoneyTransferFacility;
import instapay.Modules.User.User;

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
                    } else {
                        System.out.println("Invalid registration or Account already registered!");
                    }

                    break;
                case 2:
                    userData = Presenter.loginUser();
                    if (verifyLogin(userData)) {
                        System.out.println("Login successfull!\nWelcome " + currentlyLoggedInUser.getUsername());
                        currentlyLoggedInUser = users.getUserByUsername(userData.getUsername()).get();
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
            return false; // Account already registered
        }

        // verify password
        // verify phone

        if (user.getMoneyProvider() == null) {
            return false;
        }

        // Interacting with the ExternalAccounts is questionable though. ???
        if (!accounts.getAccountBy(user.getProviderAccountIdentifier()).isPresent()) {
            return false;
        }

        return true;
    }

    private boolean verifyLogin(User user) {
        // DANGER - calling get before checking for presence throws an exception if empty.
        User foundUser = users.getUserByUsername(user.getUsername()).get();
        if (foundUser == null) {
            return false;
        }

        if (foundUser.getPassword() != user.getPassword()) {
            return false;
        }

        return true;
    }
}
