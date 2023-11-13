package instapay.DataAccess.Models;

import instapay.Enums.MoneyProvider;

public class ExternalAccount {
    private String providerHandle;
    private double balance;
    private MoneyProvider provider;

    public ExternalAccount(String providerHandle, double balance, MoneyProvider provider) {
        this.providerHandle = providerHandle;
        this.balance = balance;
        this.provider = provider;
    }

    public MoneyProvider getProvider() {
        return provider;
    }

    public String getProviderHandle() {
        return providerHandle;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
