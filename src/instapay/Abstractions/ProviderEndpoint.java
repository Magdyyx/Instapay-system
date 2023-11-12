package instapay.Abstractions;

public abstract class ProviderEndpoint {
    private int accountNumber;
    private int userId;
    private String moneyProviderName;
    protected double balance;

    public ProviderEndpoint(int accountNumber, int userId,
                            String moneyProviderName, double balance)
    {
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.moneyProviderName = moneyProviderName;
        this.balance = balance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public int getUserId() {
        return userId;
    }

    public String getMoneyProviderName() {
        return moneyProviderName;
    }

    public boolean HasEnoughBalance(int amount) {
        if (balance < amount) {
            return false;
        } else {
            return true;
        }
    }

    public boolean Credit(int amount) {
        this.balance +=amount;
        return false;
    }

    public boolean Debit(int amount) {
        this.balance -= amount;
        return false;
    }

    public double getBalance() {
        return balance;
    }
}
