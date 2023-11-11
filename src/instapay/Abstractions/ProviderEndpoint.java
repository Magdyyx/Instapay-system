package instapay.Abstractions;

public abstract class ProviderEndpoint {
    public abstract boolean HasEnoughBalance(String accountId, int amount);

    public abstract boolean Debit(String accountId, int amount);

    public abstract boolean Credit(String accountId, int amount);

    public abstract int GetBalance(String accountId);
}
