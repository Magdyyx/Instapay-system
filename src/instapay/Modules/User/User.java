//package instapay.Modules.User;
//
//import instapay.Modules.Providers.ProviderEndpoint;
//
//public class User {
//    private int userId;
//    private String nationalID;
//    private String username;
//    private String password;
//    private String mobileNumber;
//
//    private ProviderEndpoint moneyProvider;
//
//    private String userType;
//    private boolean verified;
//
//    public User(int userID, String nationalID, String username, String password, String mobileNumber,
//                ProviderEndpoint moneyProvider, String userType, boolean verified) {
//        this.userId = userID;
//        this.nationalID = nationalID;
//        this.username = username;
//        this.password = password;
//        this.mobileNumber = mobileNumber;
//        this.moneyProvider = moneyProvider;
//        this.userType = userType;
//        this.verified = verified;
//    }
//    /*public User(int id,String username, String password, String mobileNumber, String bankAccount, String walletProvider, String userType, boolean verified) {
//        this(username, password, mobileNumber, bankAccount, walletProvider, userType, verified);
//        this.userId = id;
//    }*/
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public String getNationalID() {
//        return nationalID;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public ProviderEndpoint getMoneyProvider() {
//        return moneyProvider;
//    }
//
//    public String getMoneyProviderName() {
//        return moneyProvider.getMoneyProviderName();
//    }
//
//    public String getUserType() {
//        return userType;
//    }
//
//    public boolean isVerified() {
//        return verified;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public void setNationalID(String nationalID) {
//        this.nationalID = nationalID;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }
//
//    public void setMoneyProvider(ProviderEndpoint moneyProvider) {
//        this.moneyProvider = moneyProvider;
//    }
//
//    public void setUserType(String userType) {
//        this.userType = userType;
//    }
//
//    public void setVerified(boolean verified) {
//        this.verified = verified;
//    }
//
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + userId + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", national id='" + nationalID + '\'' +
//                ", mobileNumber='" + mobileNumber + '\'' +
//                ", userType='" + userType + '\'' +
//                ", verified=" + verified +
//                ", account balance='" + moneyProvider.getBalance() + '\'' +
//                '}';
//    }
//}