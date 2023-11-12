package instapay.Modules.MockedAPIs;

public class CIBBankAPI implements BankAPI{
    @Override
    public boolean verifyUser(String accountNumber, String mobileNumer) {
        // add implementations
        return false;
    }

    @Override
    public double getUserBalance(String accountNumber) {
        // add implementations
        return 0;
    }

    @Override
    public boolean hasEnoughBalance(String accountNumber, double amount) {
        // add implementations
        return false;
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        // add implementations
        return false;
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        // add implementations
        return false;
    }
}
