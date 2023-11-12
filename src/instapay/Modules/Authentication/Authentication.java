package instapay.Modules.Authentication;

import instapay.Modules.User.User;

public interface Authentication {
    //boolean registerUser(User user);
    // we are not allowed to add any user to the system we are dealing with
    // we just inquire about him and the app registration should be done by us not the API
    boolean verifyUser(String accountNumber);
    boolean login(String username, String password);
}