package instapay.DAOs;
import instapay.IDAOs.IUserDAO;
import instapay.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import instapay.DatabaseConnector;


public class UserDAO implements IUserDAO {
    private final Connection connection = DatabaseConnector.getConnection();


    @Override
    public User getUserById(int userId) {
        String query = "SELECT * FROM User WHERE UserID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO User (UserID,Username, Password, MobileNumber, BankAccount, WalletProvider, UserType, Verified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getMobileNumber());
            preparedStatement.setString(5, user.getBankAccount());
            preparedStatement.setString(6, user.getWalletProvider());
            preparedStatement.setString(7, user.getUserType());
            preparedStatement.setBoolean(8, user.isVerified());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int userId) {

    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("UserID"),
                resultSet.getString("Username"),
                resultSet.getString("Password"),
                resultSet.getString("MobileNumber"),
                resultSet.getString("BankAccount"),
                resultSet.getString("WalletProvider"),
                resultSet.getString("UserType"),
                resultSet.getBoolean("Verified")
        );
    }
}
