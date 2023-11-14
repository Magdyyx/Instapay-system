package instapay.Modules.Endpoints;

import instapay.Enums.MoneyProvider;

public class MockupCIBEndpoint extends ProviderEndpoint {
    private static final MoneyProvider PROVIDER = MoneyProvider.CIBBank;

    @Override
    public boolean Credit(String providerAccountIdentifier, double amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean Debit(String providerAccountIdentifier, double amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean HasEnoughBalance(String providerAccountIdentifier, double amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double GetBalance(String providerAccountIdentifier) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean VerifyAccount(String providerAccountIdentifier) {
        return true;
    }

}
