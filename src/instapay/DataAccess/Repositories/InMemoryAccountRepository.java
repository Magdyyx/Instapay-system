package instapay.DataAccess.Repositories;

import instapay.Abstractions.AccountRepository;
import instapay.DataAccess.Models.ExternalAccount;
import instapay.Enums.MoneyProvider;

import java.util.List;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {

    private static final List<ExternalAccount> accounts = List.of(
            new ExternalAccount("01013647953", 670, MoneyProvider.Fawry),
            new ExternalAccount("01213647953", 7000, MoneyProvider.Fawry),
            new ExternalAccount("IPAN123", 50, MoneyProvider.Alahly),
            new ExternalAccount("01513647953", 170, MoneyProvider.Fawry),
            new ExternalAccount("IPAN111", 778, MoneyProvider.Alahly),
            new ExternalAccount("IPAN222", 90, MoneyProvider.Alahly),
            new ExternalAccount("IPAN333", 61370, MoneyProvider.Alahly)
    );

    @Override
    public void addAccount(ExternalAccount account) {
        accounts.add(account);
    }

    @Override
    public void updateAccount(ExternalAccount account) {
        Optional<ExternalAccount> updateCandidate = accounts.stream()
                .filter(a -> a.getProviderHandle().equals(account.getProviderHandle()))
                .findFirst();

        if (updateCandidate.isPresent()) {
            ExternalAccount accountToModify = updateCandidate.get();
            accountToModify.setBalance(account.getBalance());
        }
    }

    @Override
    public Optional<ExternalAccount> getAccountBy(String providerHandle) {

        return accounts.stream()
                .filter(a -> a.getProviderHandle().equals(providerHandle))
                .findFirst();
    }
}
