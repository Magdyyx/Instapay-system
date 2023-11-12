package instapay.Modules.MockedAPIs;

public interface FinancesManagerAPI {
    public double getUserBalance(String accountNumber);

    public boolean hasEnoughBalance(String accountNumber, double amount);

    public boolean deposit(String accountNumber, double amount);

    public boolean withdraw(String accountNumber, double amount);
}
