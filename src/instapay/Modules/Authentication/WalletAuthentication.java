package instapay.Modules.Authentication;
import instapay.Modules.MockedAPIs.FinancesManagerAPI;
import instapay.Modules.MockedAPIs.WalletAPI;
import instapay.Modules.User.User;

// TODO since WalletAuthentication and Bankauthentication uses the same general API, I think
//  there should be only one authentication class
public class WalletAuthentication implements Authentication {
    private WalletAPI walletAPI;

    public WalletAuthentication(WalletAPI walletAPI) {
        this.walletAPI = walletAPI;
    }

    public static boolean Authenticate(String phoneNumber) {
        return true;
    }

    //@Override
    //public boolean registerUser(User user) {
    //    return walletFinancesManagerAPI.registerUser(user);
    //}
    // TODO
    // we are not allowed to add any user to the system we are dealing with
    // we just inquire about him and the app registration should be done by us not the API

    @Override
    public boolean verifyUser(String accountNumber) {
        return walletAPI.verifyUser(accountNumber);
    }

    @Override
    public boolean login(String username, String password) {
        // Logic for user login with the wallet
        return true; // For the sake of example, assume login is successful
    }
}
