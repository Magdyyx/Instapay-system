package instapay.Abstractions;

import instapay.DataAccess.Models.InstapayUser;

import java.util.Optional;

public interface UserRepository {

    public void addUser(InstapayUser user);

    public void updateUser(InstapayUser user);

    public Optional<InstapayUser> getUserByUsername(String username);

    public Optional<InstapayUser> getUserByAccountNumber(String accountNumber);
}
