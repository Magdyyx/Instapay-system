package instapay;

import instapay.Abstractions.ProviderEndpoint;
//import instapay.DataAccess.Repositories.UserDAO;
import instapay.DataAccess.Models.User;
import instapay.Endpoints.CIBEndpoint;
//import instapay.Enums.MoneyProvider;
import instapay.TransferFacility.MoneyTransferFacility;

public class Main {
    public static void main(String[] args) {
        ProviderEndpoint CIBSender = new CIBEndpoint(123, 123, "CIB", 500);
        ProviderEndpoint CIBReciever = new CIBEndpoint(1234, 1234, "CIB", 200);


        User daddy = new User(123, "303030","daddy", "daddy",
                "123", CIBSender, "Bank", true);

        User son = new User(1234, "505050", "son", "son",
                "124", CIBReciever, "Bank", true);
        MoneyTransferFacility moneyTransferFacility = new MoneyTransferFacility();
        moneyTransferFacility.TransferMoney(daddy, son, 300);
        System.out.println(daddy);
        System.out.println(son);
//        UserDAO userDAO = new UserDAO();
//
//        //Get a user by ID
//        User userById = userDAO.getUserById(1);
//        System.out.println("User by ID: " + userById);

//        //Get all users
//        List<User> allUsers = userDAO.getAllUsers();
//        System.out.println("All users: " + allUsers);

//        //Add a new user
//        User newUser = new User("newUser", "newPassword", "newEmail", "newPhone", "newAddress", "newCity",true );
//        userDAO.addUser(newUser);
//        System.out.println("User added: " + newUser);
//
//        //Update user details
//        User userToUpdate = userDAO.getUserById(2);
//        userToUpdate.setUsername("ahmed");
//        userDAO.updateUser(userToUpdate);
//        System.out.println("User updated: " + userToUpdate);

        // Example: Delete a user by ID
//        int userIdToDelete = 2;
//        userDAO.deleteUser(userIdToDelete);
//        System.out.println("User deleted with ID: " + userIdToDelete);
    }
}
