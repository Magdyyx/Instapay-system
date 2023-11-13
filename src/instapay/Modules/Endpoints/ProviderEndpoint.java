package instapay.Modules.Endpoints;

import instapay.Modules.Repositories.AccountRepository;
import instapay.Modules.Repositories.InMemoryAccountRepository;

public abstract class ProviderEndpoint {
    protected final AccountRepository accountRepository = new InMemoryAccountRepository();

    public abstract boolean HasEnoughBalance(String providerAccountIdentifier, double amount);

    public abstract boolean Debit(String providerAccountIdentifier, double amount);

    public abstract boolean Credit(String providerAccountIdentifier, double amount);

    public abstract double GetBalance(String providerAccountIdentifier);

    public abstract boolean VerifyAccount(String providerAccountIdentifier);
}
