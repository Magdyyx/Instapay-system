package instapay.Modules.Repositories;

import instapay.Modules.User.User;

import java.util.Optional;

public interface UserRepository {

    public void addUser(User user);

    public void updateUser(User user);

    public Optional<User> getUserByUsername(String username);

    public Optional<User> getUserByProviderAccountIdentifier(String providerAccountIdentifier);
}
