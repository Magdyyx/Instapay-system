package instapay.Modules.Account;

import instapay.Enums.MoneyProvider;

public class ExternalAccount {
    private String providerAccountIdentifier;
    private double balance;
    private MoneyProvider provider;

    public ExternalAccount(String providerAccountIdentifier, double balance, MoneyProvider provider) {
        this.providerAccountIdentifier = providerAccountIdentifier;
        this.balance = balance;
        this.provider = provider;
    }

    public MoneyProvider getProvider() {
        return provider;
    }

    public String getProviderAccountIdentifier() {
        return providerAccountIdentifier;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
