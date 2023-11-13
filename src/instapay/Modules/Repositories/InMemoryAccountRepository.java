package instapay.Modules.Repositories;

import instapay.Modules.Account.ExternalAccount;
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
                .filter(a -> a.getProviderAccountIdentifier().equals(account.getProviderAccountIdentifier()))
                .findFirst();

        if (updateCandidate.isPresent()) {
            ExternalAccount accountToModify = updateCandidate.get();
            accountToModify.setBalance(account.getBalance());
        }
    }

    @Override
    public Optional<ExternalAccount> getAccountBy(String providerAccountIdentifier) {

        return accounts.stream()
                .filter(a -> a.getProviderAccountIdentifier().equals(providerAccountIdentifier))
                .findFirst();
    }
}