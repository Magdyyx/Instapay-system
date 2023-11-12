package instapay.Modules.MockedAPIs;
import instapay.Modules.User.User;

public interface API {
    boolean verifyUser(User user);
    boolean registerUser(User user);
}