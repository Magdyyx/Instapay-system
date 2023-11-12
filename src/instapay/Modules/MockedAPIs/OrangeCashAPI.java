package instapay.Modules.MockedAPIs;

public class OrangeCashAPI implements WalletAPI{
    @Override
    public boolean verifyUser(String mobileNumber) {
        // add implementations

        return false;
    }

    @Override
    public double getUserBalance(String mobileNumber) {
        // add implementations
        return 0;
    }

    @Override
    public boolean hasEnoughBalance(String mobileNumber, double amount) {
        // add implementations
        return false;
    }

    @Override
    public boolean deposit(String mobileNumber, double amount) {
        // add implementations
        return false;
    }

    @Override
    public boolean withdraw(String mobileNumber, double amount) {
        // add implementations
        return false;
    }
}
