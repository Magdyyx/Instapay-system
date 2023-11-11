package instapay.DataAccess.Models;

public class User {
    private int userId;
    private String username;
    private String password;
    private String mobileNumber;
    private String bankAccount;
    private String walletProvider;
    private String userType;
    private boolean verified;

    public User(String username, String password, String mobileNumber, String bankAccount, String walletProvider, String userType, boolean verified) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.bankAccount = bankAccount;
        this.walletProvider = walletProvider;
        this.userType = userType;
        this.verified = verified;
    }
    public User(int id,String username, String password, String mobileNumber, String bankAccount, String walletProvider, String userType, boolean verified) {
        this(username, password, mobileNumber, bankAccount, walletProvider, userType, verified);
        this.userId = id;
    }

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
    public String getBankAccount() {
        return bankAccount;
    }
    public String getWalletProvider() {
        return walletProvider;
    }
    public String getUserType() {
        return userType;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    public void setWalletProvider(String walletProvider) {
        this.walletProvider = walletProvider;
    }
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
                ", bankAccount='" + bankAccount + '\'' +
                ", walletProvider='" + walletProvider + '\'' +
                ", userType='" + userType + '\'' +
                ", verified=" + verified +
                '}';
    }


}