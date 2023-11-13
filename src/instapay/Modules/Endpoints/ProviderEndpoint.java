package instapay.Modules.Endpoints;

import instapay.Modules.Repositories.AccountRepository;
import instapay.Modules.Repositories.InMemoryAccountRepository;

public abstract class ProviderEndpoint {
    protected final AccountRepository accountRepository = new InMemoryAccountRepository();

    public abstract boolean HasEnoughBalance(String accountNumber, double amount);

    public abstract boolean Debit(String accountNumber, double amount);

    public abstract boolean Credit(String accountNumber, double amount);

    public abstract double GetBalance(String accountNumber);

    public abstract boolean VerifyAccount(String accountNumber);
}