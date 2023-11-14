package instapay.Modules.Endpoints;

import instapay.Enums.MoneyProvider;
import instapay.Modules.Account.ExternalAccount;

import java.util.Optional;

public class MockupFawryEndpoint extends ProviderEndpoint {
    private static final MoneyProvider PROVIDER = MoneyProvider.Fawry;

    @Override
    public boolean Credit(String providerAccountIdentifier, double amount) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER);
        if (accountOptional.isPresent()) {
            ExternalAccount toUpdate = accountOptional.get();
            toUpdate.setBalance(toUpdate.getBalance() + amount);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Debit(String providerAccountIdentifier, double amount) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER);
        if (accountOptional.isPresent()) {
            ExternalAccount toUpdate = accountOptional.get();
            toUpdate.setBalance(toUpdate.getBalance() - amount);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean HasEnoughBalance(String providerAccountIdentifier, double amount) {
        return accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER)
                .filter(account -> account.getBalance() >= amount)
                .isPresent();
    }

    @Override
    public double GetBalance(String providerAccountIdentifier) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER);
        return accountOptional.map(ExternalAccount::getBalance).orElse(0.0);
    }

    @Override
    public boolean VerifyAccount(String providerAccountIdentifier) {
        return true;
    }

}
