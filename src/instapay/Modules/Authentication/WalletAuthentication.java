package instapay.Modules.Authentication;
import instapay.Modules.MockedAPIs.API;
import instapay.Modules.User.User;

public class WalletAuthentication implements Authentication {
    private API walletAPI;

    public WalletAuthentication(API walletAPI) {
        this.walletAPI = walletAPI;
    }

    public static boolean Authenticate(String phonenumber) {
        return true;
    }

    @Override
    public boolean registerUser(User user) {
        return walletAPI.registerUser(user);
    }

    @Override
    public boolean verifyUser(User user) {
        return walletAPI.verifyUser(user);
    }

    @Override
    public boolean login(String username, String password) {
        // Logic for user login with the wallet
        return true; // For the sake of example, assume login is successful
    }
}
