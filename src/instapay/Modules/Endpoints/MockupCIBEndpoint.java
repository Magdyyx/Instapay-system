package instapay.Modules.Endpoints;

public class MockupCIBEndpoint extends ProviderEndpoint {

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
