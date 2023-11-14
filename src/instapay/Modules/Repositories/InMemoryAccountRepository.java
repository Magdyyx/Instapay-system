package instapay.Modules.Repositories;

import instapay.Modules.Account.ExternalAccount;
import instapay.Enums.MoneyProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {

    private static final List<ExternalAccount> accounts = init();

    private static List<ExternalAccount> init() {
        ArrayList<ExternalAccount> accounts = new ArrayList<>();
        accounts.add(new ExternalAccount("01013647953", 670, MoneyProvider.Fawry));
        accounts.add(new ExternalAccount("01213647953", 7000, MoneyProvider.Fawry));
        accounts.add(new ExternalAccount("IPAN123", 50, MoneyProvider.Alahly));
        accounts.add(new ExternalAccount("01513647953", 170, MoneyProvider.Fawry));
        accounts.add(new ExternalAccount("IPAN111", 778, MoneyProvider.Alahly));
        accounts.add(new ExternalAccount("IPAN222", 90, MoneyProvider.Alahly));
        accounts.add(new ExternalAccount("IPAN333", 61370, MoneyProvider.Alahly));
        accounts.add(new ExternalAccount("IPAN444", 7500, MoneyProvider.Alahly));

        return accounts;
    }

    @Override
    public void addAccount(ExternalAccount account) {
        accounts.add(account);
    }

    @Override
    public void updateAccount(ExternalAccount account) {
        Optional<ExternalAccount> updateCandidate = accounts.stream()
                .filter(a -> a.getProvider().equals(account.getProvider()))
                .filter(a -> a.getProviderAccountIdentifier().equals(account.getProviderAccountIdentifier()))
                .findFirst();

        if (updateCandidate.isPresent()) {
            ExternalAccount accountToModify = updateCandidate.get();
            accountToModify.setBalance(account.getBalance());
        }
    }

    @Override
    public Optional<ExternalAccount> getAccountBy(String providerAccountIdentifier, MoneyProvider provider) {

        return accounts.stream()
                .filter(a -> a.getProvider().equals(provider))
                .filter(a -> a.getProviderAccountIdentifier().equals(providerAccountIdentifier))
                .findFirst();
    }
}
