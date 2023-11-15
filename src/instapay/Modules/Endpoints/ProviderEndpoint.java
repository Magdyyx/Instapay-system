package instapay.Modules.Endpoints;

import instapay.Modules.Repositories.AccountRepository;
import instapay.Modules.Repositories.InMemoryAccountRepository;
import instapay.Modules.Response.Response;

public abstract class ProviderEndpoint {
    protected final AccountRepository accountRepository = new InMemoryAccountRepository();

    public abstract Response HasEnoughBalance(String providerAccountIdentifier, double amount);

    public abstract Response Debit(String providerAccountIdentifier, double amount);

    public abstract Response Credit(String providerAccountIdentifier, double amount);

    public abstract Response GetBalance(String providerAccountIdentifier);

    public abstract Response VerifyAccount(String providerAccountIdentifier);
}
