package instapay.Modules.User;

public class User {
    private String username;
    private String password;
    private String mobileNumber;

    //constructor
    public User(String username, String password, String mobileNumber) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
    //getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    //setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

