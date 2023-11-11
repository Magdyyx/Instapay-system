package instapay.DataAccess.Repositories;

import instapay.DatabaseConnector;
import instapay.IDAOs.IBankAccountDAO;
import instapay.DataAccess.Models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDAO implements IBankAccountDAO {
    private final Connection connection = DatabaseConnector.getConnection();

    @Override
    public BankAccount getBankAccountByUserId(int userId) {
        String query = "SELECT * FROM BankAccount WHERE userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int accountNumber = resultSet.getInt("accountNumber");
                String bankName = resultSet.getString("bankName");

                return new BankAccount(accountNumber, userId, bankName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addBankAccount(BankAccount bankAccount) {
        String query = "INSERT INTO BankAccount (accountNumber, userId, bankName) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bankAccount.getUserId());
            preparedStatement.setString(2, bankAccount.getBankName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        String query = "UPDATE BankAccount SET accountNumber = ?, bankName = ? WHERE userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bankAccount.getAccountNumber());
            preparedStatement.setString(2, bankAccount.getBankName());
            preparedStatement.setInt(3, bankAccount.getUserId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBankAccount(int userId) {
        String query = "DELETE FROM BankAccount WHERE userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
