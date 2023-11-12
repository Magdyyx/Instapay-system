package instapay.Endpoints;

import instapay.Abstractions.MoneyAccount;
import instapay.Abstractions.ProviderEndpoint;

public class CIBEndpoint extends ProviderEndpoint {


    public CIBEndpoint(int accountNumber, int userId,
                          String moneyProviderName, double balance) {
        super(accountNumber, userId, moneyProviderName, balance);
    }
    @Override
    public boolean Credit(int amount) {
        this.balance +=amount;
        return false;
    }

    @Override
    public boolean Debit(int amount) {
        this.balance -= amount;
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean HasEnoughBalance(int amount) {
        if (balance < amount) {
            return false;
        } else {
            return true;
        }
        // TODO Auto-generated method stub
    }

    /*@Override
    public int GetBalance(String accountId) {
        // TODO Auto-generated method stub
        return 0;
    }*/
}
