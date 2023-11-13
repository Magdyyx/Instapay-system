package instapay.Modules.Repositories;

import instapay.Modules.User.InstapayUser;

import java.util.Optional;

public interface UserRepository {

    public void addUser(InstapayUser user);

    public void updateUser(InstapayUser user);

    public Optional<InstapayUser> getUserByUsername(String username);

    public Optional<InstapayUser> getUserByProviderHandle(String providerHandle);
}
