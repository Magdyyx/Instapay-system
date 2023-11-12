package instapay.Modules.Authentication;

import instapay.Modules.User.User;

public interface Authentication {
    boolean registerUser(User user);
    boolean verifyUser(User user);
    boolean login(String username, String password);
}