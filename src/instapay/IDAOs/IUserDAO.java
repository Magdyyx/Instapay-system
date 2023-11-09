package instapay.IDAOs;
import java.util.List;
import instapay.Model.User;

public interface IUserDAO {
    User getUserById(int userId);
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}