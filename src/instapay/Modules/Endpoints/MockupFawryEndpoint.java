package instapay.Modules.Endpoints;

import instapay.Modules.Account.ExternalAccount;

import java.util.Optional;

public class MockupFawryEndpoint extends ProviderEndpoint {

    @Override
    public boolean Credit(String accountNumber, double amount) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(accountNumber);
        if (accountOptional.isPresent()) {
            ExternalAccount toUpdate = accountOptional.get();
            toUpdate.setBalance(toUpdate.getBalance() + amount);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Debit(String accountNumber, double amount) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(accountNumber);
        if (accountOptional.isPresent()) {
            ExternalAccount toUpdate = accountOptional.get();
            toUpdate.setBalance(toUpdate.getBalance() - amount);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean HasEnoughBalance(String accountNumber, double amount) {
        return accountRepository.getAccountBy(accountNumber)
                .filter(account -> account.getBalance() >= amount)
                .isPresent();
    }

    @Override
    public double GetBalance(String accountNumber) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(accountNumber);
        return accountOptional.map(ExternalAccount::getBalance).orElse(0.0);
    }

    @Override
    public boolean VerifyAccount(String accountNumber) {
        return true;
    }

}
