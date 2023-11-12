package instapay;

import instapay.Modules.Authentication.NewUserAuthentication;
import instapay.Modules.BillPayment.Bill;
import instapay.Modules.Authentication.Authentication;
import instapay.Modules.Authentication.BankAuthentication;
import instapay.Modules.Authentication.WalletAuthentication;
import instapay.Modules.BillPayment.Bill;
import instapay.Modules.BillPayment.BillPaymentService;
import instapay.Modules.BillPayment.GasBill;
import instapay.Modules.MockedAPIs.BankMockedAPI;
import instapay.Modules.MockedAPIs.WalletMockedAPI;
import instapay.Modules.TransferStrategy.TransferToInstapayAccount;
import instapay.Modules.User.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Mocked APIs for testing
//        BankMockedAPI bankMockedAPI = new BankMockedAPI();
//        WalletMockedAPI walletMockedAPI = new WalletMockedAPI();
//        // Authentication
//        Authentication bankauthService = new BankAuthentication(bankMockedAPI);
//        Authentication walletAuthService = new WalletAuthentication(walletMockedAPI);

        // Transfer
        TransferToInstapayAccount transferStrategy = new TransferToInstapayAccount();

        // Bill Payment
//        Bill gasBill = new GasBill("Gas", 100);
//        BillPaymentService billPaymentService = new BillPaymentService();
//        billPaymentService.payBill(gasBill);
//User registers username, password, phone number, and OTP
        System.out.println("Welcome to Instapay!");
        System.out.println("Would you like to Register or Log in?");
        System.out.println("1 - Register");
        System.out.println("2 - Login");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
        }
    }

    private static void registerUser() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Register using your bank account or wallet?");
            System.out.println("1 - Bank Account");
            System.out.println("2 - Wallet");

            int registrationChoice = scanner.nextInt();

            switch (registrationChoice) {
                case 1:
                    registerBankUser();
                    break;
                case 2:
                    registerWalletUser();
                    break;
                default:
                    System.out.println("Invalid choice. Exiting registration...");
            }
        }


    private static void registerBankUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your details to register:");
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter OTP sent to your phone number");
        String otp = scanner.nextLine();
        if (NewUserAuthentication.Authenticate(phoneNumber, otp)) {
            System.out.println("Authentication successful!");
            System.out.println("Enter your account number:");
            String accountNumber = scanner.nextLine();
            System.out.println("Enter your bank name:");
            String bankName = scanner.nextLine();
            System.out.println("Enter your NationalID:");
            String NationalID = scanner.nextLine();

            if (BankAuthentication.Authenticate(accountNumber, bankName, NationalID)){
                System.out.println("Authentication successful!");
                System.out.println("Registration successful! You can now log in.");
                loginUser();
            } else {
                System.out.println("Authentication failed. Exiting registration...");
                return;
            }


            }else {
            System.out.println("Authentication failed. Exiting registration...");
            }



        }

    private static void registerWalletUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your details to register:");
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter OTP sent to your phone number");
        String otp = scanner.nextLine();
        if (NewUserAuthentication.Authenticate(phoneNumber, otp)) {
            System.out.println("Authentication successful!");
            System.out.println("Enter your Wallet Provider:");
            String walletProvider = scanner.nextLine();

            System.out.println("Enter your balance:");
            int balance = scanner.nextInt();
            if (WalletAuthentication.Authenticate(walletProvider, balance)){
                System.out.println("Authentication successful!");
                System.out.println("Registration successful! You can now log in.");
                loginUser();
            } else {
                System.out.println("Authentication failed. Exiting registration...");
                return;
            }


        }else {
            System.out.println("Authentication failed. Exiting registration...");
        }




    }

    private static void loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        // Additional logic for user login
        // For now, let's assume login is successful

        System.out.println("Login successful! Welcome back, " + username + "!");
    }

    }

