package instapay.Modules.Authentication;
import instapay.Modules.MockedAPIs.BankAPI;
import instapay.Modules.MockedAPIs.FinancesManagerAPI;

// I replaced the former API with FinancesManagerAPI which is more general and object-oriented
// TODO since WalletAuthentication and Bankauthentication uses the same general API, I think
//  there should be only one authentication class

public class BankAuthentication implements Authentication{
    private BankAPI bankAPI;

    public BankAuthentication(BankAPI bankAPI) {
        this.bankAPI = bankAPI;
    }

    public static boolean Authenticate(String accountNumber, String bankName,String NationalID) {
        return true;
    }

    //@Override
    //public boolean registerUser(User user) {
    //    return bankAPI.registerUser(user);
    //}
    // TODO we are not allowed to add any user to the system we are dealing with
    // we just inquire about him and the app registration should be done by us not the API


    // TODO It's stated in the requirements that we should verify account num and phone num for bank users
    // consider handling it
    /*@Override
    public boolean verifyUser(String accountNumber) {
        return bankAPI.verifyUser(accountNumber);
    }*/

    // verify user takes an account

    @Override
    public boolean login(String username, String password) {
        // Logic for user login with the bank
        return true;
    }

}
