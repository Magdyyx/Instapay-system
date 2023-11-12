package instapay;

import instapay.Modules.Authentication.NewUserAuthentication;
import instapay.Modules.Authentication.BankAuthentication;
import instapay.Modules.Authentication.WalletAuthentication;
import instapay.Modules.MockedAPIs.*;
import instapay.Modules.TransferStrategy.TransferToInstapayAccount;

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
        //TODO this class should be deleted and user MoneyTransferFacility instead

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

        //TODO there is a lot of code that is repeated
        // the only difference between bank registration and wallet registration is:
        // in bank registration we take only one extra info "the bank account number", then authenticate(accountNum, mobileNum)
        // in wallet registration we set the mobile number to the account number, then authenticate(mobileNum)
        // so make a third function that gathers all the info instead of these differences and seperate
        // these differences in other function
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

            //TODO add a choice list with the registered banks and make the user choose to avoid bank name errors
            //"1- CIB bank 2-Albank Alahly ..."
            // the names should exactly match the name in the factory
            // then send this name to the factory i added bellow

            if (BankAuthentication.Authenticate(accountNumber, bankName, NationalID)){
                System.out.println("Authentication successful!");
                //TODO TO BE IMPLEMENTED
//                API bankAPI = apifactory(bankName);
//                ProviderEndpoint Provider = new ProviderEndpoint(accountNumber,bank name,bankAPI);
//                User user = new User('2',NationalID,username,password,phoneNumber,Provider,"BankUser",true);
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
            String accountNumber = phoneNumber; // TO BE EDITED
            System.out.println("Enter your Wallet name:");
            String walletName = scanner.nextLine();
            System.out.println("Enter your NationalID:");
            String NationalID = scanner.nextLine();

            if (WalletAuthentication.Authenticate(phoneNumber)){
                System.out.println("Authentication successful!");
                //TODO TO BE IMPLEMENTED
//                API walletAPI = apifactory(walletName);
//                ProviderEndpoint Provider = new ProviderEndpoint(accountNumber,walletName,walletAPI);
//                User user = new User('2',NationalID,username,password,phoneNumber,Provider,"WalletUser",true);
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

    private static BankAPI bankAPIFactory(String bankName) {
        bankName = bankName.toLowerCase();
        BankAPI bankAPI = null;
        if (bankName.equals("cib bank")) {
            bankAPI = new CIBBankAPI();
        } else if (bankName.equals("albank alahly")) {
            bankAPI = new AlahlyBankAPI();
        }/* else if () {

        } else if () {

        }*/
        // if other bankAPIs is added
        return bankAPI;
    }

    private static WalletAPI walletAPIFactory(String walletName){
        walletName = walletName.toLowerCase();
        WalletAPI walletAPI = null;
        if (walletName.equals("etisalat")) {
            walletAPI = new EtisalatCashAPI();
        } else if (walletName.equals("vodafone")) {
            walletAPI = new VodafoneCashAPI();
        } else if (walletName.equals("orange")) {
            walletAPI = new OrangeCashAPI();
        }

        return walletAPI;
    }


    }

