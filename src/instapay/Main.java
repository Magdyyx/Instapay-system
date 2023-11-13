package instapay;

import instapay.DataAccess.Repositories.UserDAO;
import instapay.DataAccess.Models.User;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//
//        //Get a user by ID
//        User userById = userDAO.getUserById(1);
//        System.out.println("User by ID: " + userById);

//        //Get all users
//        List<User> allUsers = userDAO.getAllUsers();
//        System.out.println("All users: " + allUsers);

//        //Add a new user
        User newUser = new User("newUser", "newPassword", "newEmail", "newPhone", "newAddress", "newCity",true );
        userDAO.addUser(newUser);
        System.out.println("User added: " + newUser);
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
