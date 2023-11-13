package instapay;

import java.util.Scanner;

import instapay.Enums.MoneyProvider;
import instapay.Modules.User.User;

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

        System.out.println("Select a money provider:");
        for (int i = 0; i < moneyProviders.length; i++) {
            System.out.println((i + 1) + ". " + moneyProviders[i]);
        }

        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
        }

        if (choice >= 1 && choice <= moneyProviders.length) {
            return moneyProviders[choice - 1];
        } else {
            return null;
        }
    }

    public static User loginUser() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        return new User(username, password, null, null, null);
    }
}
