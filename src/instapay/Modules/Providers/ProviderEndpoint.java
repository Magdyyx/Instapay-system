package instapay.Modules.Providers;

import instapay.Modules.MockedAPIs.FinancesManagerAPI;
import instapay.Modules.User.User;

public abstract class ProviderEndpoint {
    private String accountNumber;
    private String moneyProviderName;
    private FinancesManagerAPI providerAPI;
    private double balance;

    public ProviderEndpoint(String accountNumber, String moneyProviderName,
                            FinancesManagerAPI providerAPI, double balance)
    {
        this.accountNumber = accountNumber;
        this.moneyProviderName = moneyProviderName;
        this.providerAPI = providerAPI;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }



    public String getMoneyProviderName() {
        return moneyProviderName;
    }

    public double getBalance() {
        return balance;
    }

    public FinancesManagerAPI getProviderAPI() {return providerAPI;}


    public boolean HasEnoughBalance(double amount) {
        if(providerAPI.hasEnoughBalance(this.accountNumber, amount)) {
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (providerAPI.deposit(this.accountNumber, amount)) {
            updateBalance();
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (providerAPI.withdraw(this.accountNumber, amount)) {
            updateBalance();
            return true;
        }
        return false;
    }

    private void updateBalance(){
        this.balance = providerAPI.getUserBalance(accountNumber);
    }
}


