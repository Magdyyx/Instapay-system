package instapay.Modules.MockedAPIs;

import instapay.Modules.User.User;

public class WalletMockedAPI implements API {
    @Override
    public boolean verifyUser(User user) {
        // Mocked logic for verifying user with the bank
        // Return true for successful verification, false otherwise
        return true;
    }

    @Override
    public boolean registerUser(User user) {
        // Mocked logic for registering user with the bank
        // Return true for successful registration, false otherwise
        return true;
    }
}