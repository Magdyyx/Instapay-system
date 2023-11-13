package instapay.Modules.Endpoints;

public class MockupCIBEndpoint extends ProviderEndpoint {

    @Override
    public boolean Credit(String accountNumber, double amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean Debit(String accountNumber, double amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean HasEnoughBalance(String accountNumber, double amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double GetBalance(String accountNumber) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean VerifyAccount(String accountNumber) {
        return true;
    }

}
