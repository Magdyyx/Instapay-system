package instapay;

import instapay.Abstractions.UserRepository;
import instapay.DataAccess.Models.InstapayUser;
import instapay.DataAccess.Repositories.InMemoryUserRepository;
import instapay.TransferFacility.InstapayTransferFacility;
import instapay.TransferFacility.MoneyTransferFacility;

public class Main {
    public static void main(String[] args) {
        UserRepository users = new InMemoryUserRepository();
        MoneyTransferFacility facility = new InstapayTransferFacility();

        InstapayUser currentlyLoggedInUser = users.getUserByUsername("Kilany").get();

        double userBalance = facility.InquireBalance(currentlyLoggedInUser.getProviderHandle());
        System.out.println(currentlyLoggedInUser + " | Balance: " + userBalance);

        facility.PayBill(currentlyLoggedInUser.getProviderHandle(), 1);
        facility.PayBill(currentlyLoggedInUser.getProviderHandle(), 3);

        userBalance = facility.InquireBalance(currentlyLoggedInUser.getProviderHandle());
        System.out.println(currentlyLoggedInUser + " | Balance: " + userBalance);



        System.out.println(facility.GetBill(1));
        System.out.println(facility.GetBill(3));

        System.out.println();


        System.out.println("Before Transfer_____");
        System.out.println(facility.InquireBalance("01213647953"));
        System.out.println();
        facility.TransferMoney(currentlyLoggedInUser.getProviderHandle(), "01213647953", 100);

        System.out.println("After Transfer_____");
        System.out.println(facility.InquireBalance(currentlyLoggedInUser.getProviderHandle()));
        System.out.println(facility.InquireBalance("01213647953"));
    }
}
