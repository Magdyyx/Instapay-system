package instapay.Modules.Repositories;

import instapay.Modules.User.User;
import instapay.Enums.MoneyProvider;

import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private static final List<User> users = List.of(
            new User("Kilany","pass", "01013647953", MoneyProvider.Fawry, "01013647953"),
            new User("Hamasa","pass", "01213647953", MoneyProvider.Fawry, "01213647953"),
            new User("Hamada","pass", "01513647953", MoneyProvider.Fawry, "01513647953"),
            new User("Monte","pass", "01022507780", MoneyProvider.Alahly, "IPAN111"),
            new User("Carlo","pass", "01210005043", MoneyProvider.Alahly, "IPAN222"),
            new User("Mano","pass", "01022676843", MoneyProvider.Alahly, "IPAN333")
    );

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        Optional<User> updateCandidate = users.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByProviderAccountIdentifier(String providerAccountIdentifier) {
        return users.stream()
                .filter(u -> u.getProviderAccountIdentifier().equals(providerAccountIdentifier))
                .findFirst();
    }
}
