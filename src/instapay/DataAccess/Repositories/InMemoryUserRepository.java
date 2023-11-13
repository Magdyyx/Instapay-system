package instapay.DataAccess.Repositories;

import instapay.Abstractions.UserRepository;
import instapay.DataAccess.Models.InstapayUser;
import instapay.Enums.MoneyProvider;

import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private static final List<InstapayUser> users = List.of(
            new InstapayUser("Kilany","pass", "01013647953", MoneyProvider.Fawry, "01013647953"),
            new InstapayUser("Hamasa","pass", "01213647953", MoneyProvider.Fawry, "01213647953"),
            new InstapayUser("Hamada","pass", "01513647953", MoneyProvider.Fawry, "01513647953"),
            new InstapayUser("Monte","pass", "01022507780", MoneyProvider.Alahly, "IPAN111"),
            new InstapayUser("Carlo","pass", "01210005043", MoneyProvider.Alahly, "IPAN222"),
            new InstapayUser("Mano","pass", "01022676843", MoneyProvider.Alahly, "IPAN333")
    );

    @Override
    public void addUser(InstapayUser user) {
        users.add(user);
    }

    @Override
    public void updateUser(InstapayUser user) {
        Optional<InstapayUser> updateCandidate = users.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();
    }

    @Override
    public Optional<InstapayUser> getUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<InstapayUser> getUserByAccountNumber(String accountNumber) {
        return users.stream()
                .filter(u -> u.getAccountNumber().equals(accountNumber))
                .findFirst();
    }
}
