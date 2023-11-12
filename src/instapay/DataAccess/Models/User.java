package instapay.DataAccess.Models;

import instapay.Abstractions.MoneyAccount;
import instapay.Abstractions.ProviderEndpoint;

public class User {
    private int userId;
    private String username;
    private String password;
    private String mobileNumber;

    private ProviderEndpoint moneyProvider;
    // private String moneyProvider;
    // private String bankAccount;
    // private String walletProvider;
    private String userType;
    private boolean verified;

    public User(int userID, String username, String password, String mobileNumber,
                ProviderEndpoint moneyProvider, String userType, boolean verified)
    {
        this.userId = userID;
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.moneyProvider = moneyProvider;
        //this.bankAccount = bankAccount;
        //this.walletProvider = walletProvider;
        this.userType = userType;
        this.verified = verified;
    }
    /*public User(int id,String username, String password, String mobileNumber, String bankAccount, String walletProvider, String userType, boolean verified) {
        this(username, password, mobileNumber, bankAccount, walletProvider, userType, verified);
        this.userId = id;
    }*/

    public String getUsername() {
        return username;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    //public String getBankAccount() {
    //    return bankAccount;
    //}
    //public String getWalletProvider() {
    //    return walletProvider;
    //}
    public String getUserType() {
        return userType;
    }

    public ProviderEndpoint getMoneyProvider() {
        return moneyProvider;
    }

    public String getMoneyProviderName() { return moneyProvider.getMoneyProviderName();}
    public boolean isVerified() {
        return verified;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    //public void setBankAccount(String bankAccount) {
    //    this.bankAccount = bankAccount;
    //}
    //public void setWalletProvider(String walletProvider) {
    //    this.walletProvider = walletProvider;
    //}
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                //", bankAccount='" + bankAccount + '\'' +
                //", walletProvider='" + walletProvider + '\'' +
                ", userType='" + userType + '\'' +
                ", verified=" + verified +
                ", account balance" + moneyProvider.getBalance() +
                '}';
    }
}