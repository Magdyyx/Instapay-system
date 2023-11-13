package instapay.DataAccess.Repositories;

import instapay.Abstractions.AccountRepository;
import instapay.DataAccess.Models.ExternalAccount;

import java.util.List;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {

    private static final List<ExternalAccount> accounts = List.of(
            new ExternalAccount("01013647953", 670),
            new ExternalAccount("01213647953", 7000),
            new ExternalAccount("IPAN123", 50),
            new ExternalAccount("01513647953", 170),
            new ExternalAccount("IPAN111", 778),
            new ExternalAccount("IPAN222", 90),
            new ExternalAccount("IPAN333", 61370)
    );

    @Override
    public void addAccount(ExternalAccount account) {
        accounts.add(account);
    }

    @Override
    public void updateAccount(ExternalAccount account) {
        Optional<ExternalAccount> updateCandidate = accounts.stream()
                .filter(a -> a.getAccountNumber().equals(account.getAccountNumber()))
                .findFirst();

        if (updateCandidate.isPresent()) {
            ExternalAccount accountToModify = updateCandidate.get();
            accountToModify.setBalance(account.getBalance());
        }
    }

    @Override
    public Optional<ExternalAccount> getAccountBy(String accountNumber) {

        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();
    }
}
