package instapay.Modules.User;

public class BankUser extends User {
    private int userID;
    private int NationalID;
    private String userType;
    private String accountNumber;
    private String bankName;

    // Constructor
    public BankUser(String username, String password, String mobileNumber, String accountNumber, String bankName) {
        super(username, password, mobileNumber);
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getUserID() {
        return userID;
    }
    public void setNationalID(int nationalID) {
        NationalID = nationalID;
    }
    public int getNationalID() {
        return NationalID;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getUserType() {
        return userType;
    }





}