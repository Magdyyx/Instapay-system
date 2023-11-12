package instapay.Endpoints;

import instapay.Abstractions.ProviderEndpoint;

public class FawryEndpoint extends ProviderEndpoint {


    public FawryEndpoint(int accountNumber, int userId,
                          String moneyProviderName, double balance) {
        super(accountNumber, userId, moneyProviderName, balance);
    }
    @Override
    public boolean Credit(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean Debit(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean HasEnoughBalance(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    /*@Override
    public int GetBalance(String accountId) {
        // TODO Auto-generated method stub
        return 0;
    }*/
}
