package instapay;

import java.util.Scanner;

import instapay.Enums.BillsEnum;
import instapay.Enums.MoneyProvider;
import instapay.Enums.UserOperation;
import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.User.User;
import instapay.Modules.ViewModels.BillViewModel;
import instapay.Modules.ViewModels.MoneyTransferViewModel;

public class Presenter {
    private static final Scanner scanner = new Scanner(System.in);

    private Presenter() {
    }

    public static int welcome() {
        System.out.println("\n\nWelcome to Instapay!");
        System.out.println("Would you like to Register or Log in?");
        System.out.println("1) Register");
        System.out.println("2) Login");
        System.out.println("0) Exit");

        int choice = Integer.MAX_VALUE;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch(NumberFormatException e) {
        }

        return choice;
    }

    public static UserOperation promptUserOperations() {
        UserOperation[] operations = UserOperation.values();
        int choice = 0;

        while (choice < 1 || choice > operations.length) {
            System.out.println("Select an operation:");
            for (int i = 0; i < operations.length; i++) {
                System.out.println((i + 1) + ". " + operations[i]);
            }

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        }

        return operations[choice - 1];
    }

    public static void showUserInfo(User user) {
        System.out.println(user.toString());
    }

    public static User registerUser() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        MoneyProvider moneyProvider = promptForMoneyProvider();

        System.out.print("Enter provider account identifier: ");
        String providerAccountIdentifier = scanner.nextLine();

        return new User(username, password, phone, moneyProvider, providerAccountIdentifier);
    }

    private static MoneyProvider promptForMoneyProvider() {
        MoneyProvider[] moneyProviders = MoneyProvider.values();

        int choice = 0;
        while (choice < 1 || choice > moneyProviders.length) {
            System.out.println("Select a money provider:");
            for (int i = 0; i < moneyProviders.length; i++) {
                System.out.println((i + 1) + ". " + moneyProviders[i]);
            }

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        }

        return moneyProviders[choice - 1];
    }

    public static User loginUser() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        return new User(username, password, null, null, null);
    }

    public static void displayBalance(double balance) {
        System.out.printf("Your balance is: %1$,.2f", balance);
    }

    public static BillViewModel promptForBillInfo() {
        BillViewModel billInfo = new BillViewModel();
        int billId = Integer.MIN_VALUE;
        while (billId < 0) {
            System.out.print("Enter the bill number: ");
            try {
                billId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid Number.");
            }
        }

        billInfo.setBillId(billId);

        BillsEnum[] types = BillsEnum.values();
        int choice = 0;

        while (choice < 1 || choice > types.length) {
            System.out.println("Select a utility:");
            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + ". " + types[i]);
            }

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        }

        billInfo.setType(types[choice - 1]);

        return billInfo;
    }

    public static boolean promptPayBill(UtilityBill bill) {
        System.out.println(bill.toString());
        System.out.println("Do you want to pay it?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = Integer.MAX_VALUE;
        while (choice == Integer.MAX_VALUE) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException ignored) {
            }
        }

        return choice == 1;
    }

    public static MoneyTransferViewModel promptForInstapayTransferInfo() {
        MoneyTransferViewModel transferInfo = new MoneyTransferViewModel();

        System.out.println("Enter the username for the user you want to transfer to:");
        transferInfo.setReceiver(scanner.nextLine());

        double choice = Integer.MIN_VALUE;
        while (choice <= 0) {
        System.out.println("Enter the amount you want to send: ");
            try {
                choice = Double.parseDouble(scanner.nextLine());
            } catch(NumberFormatException ignored) {
            }
        }

        transferInfo.setAmount(choice);

        return transferInfo;
    }

    public static MoneyTransferViewModel promptForTransferInfo() {
        MoneyTransferViewModel transferInfo = new MoneyTransferViewModel();

        transferInfo.setProvider(promptForMoneyProvider());

        System.out.println("Enter the account you want to transfer to:");
        transferInfo.setReceiver(scanner.nextLine());

        double choice = Integer.MIN_VALUE;
        while (choice <= 0) {
            System.out.println("Enter the amount you want to send: ");
            try {
                choice = Double.parseDouble(scanner.nextLine());
            } catch(NumberFormatException ignored) {
            }
        }

        transferInfo.setAmount(choice);

        return transferInfo;
    }
}
