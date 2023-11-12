package instapay.Modules.Providers;

public abstract class ProviderEndpoint {
    private int accountNumber;
    private int userNationalId;
    private String moneyProviderName;
    protected double balance;

    public ProviderEndpoint(int accountNumber, int userNationalId,
                            String moneyProviderName, double balance)
    {
        this.accountNumber = accountNumber;
        this.userNationalId = userNationalId;
        this.moneyProviderName = moneyProviderName;
        this.balance = balance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public int getUserId() {
        return userNationalId;
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
