package instapay.Modules.Authentication;
import instapay.Modules.MockedAPIs.API;
import instapay.Modules.MockedAPIs.BankMockedAPI;
import instapay.Modules.User.User;

public class BankAuthentication implements Authentication{
    private BankMockedAPI bankAPI;

    public BankAuthentication(BankMockedAPI bankAPI) {
        this.bankAPI = bankAPI;
    }

    public static boolean Authenticate(String accountNumber, String bankName,String NationalID) {
        return true;
    }

    @Override
    public boolean registerUser(User user) {
        return bankAPI.registerUser(user);
    }

    @Override
    public boolean verifyUser(User user) {
        return bankAPI.verifyUser(user);
    }

    @Override
    public boolean login(String username, String password) {
        // Logic for user login with the bank
        return true;
    }

}
