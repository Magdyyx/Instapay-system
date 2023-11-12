package instapay.IDAOs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import instapay.Modules.User.User;

public interface IUserDAO {
    User getUserById(int userId);
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    User mapResultSetToUser(ResultSet resultSet) throws SQLException;
}