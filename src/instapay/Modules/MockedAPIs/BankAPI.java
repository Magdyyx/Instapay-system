package instapay.Modules.MockedAPIs;

public interface BankAPI extends FinancesManagerAPI{
    boolean verifyUser(String accountNumber, String mobileNumber);
}
