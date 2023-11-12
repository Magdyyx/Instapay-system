package instapay.Modules.MockedAPIs;

public interface WalletAPI extends FinancesManagerAPI{
    public boolean verifyUser(String mobileNumber);
}
