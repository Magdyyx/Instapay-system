package instapay.Endpoints;

import instapay.Abstractions.ProviderEndpoint;

public class AlahlyEndpoint extends ProviderEndpoint {

    @Override
    public boolean Credit(String accountId, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean Debit(String accountId, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean HasEnoughBalance(String accountId, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int GetBalance(String accountId) {
        // TODO Auto-generated method stub
        return 0;
    }

}
