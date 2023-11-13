package instapay.DataAccess.Models;

import instapay.Enums.MoneyProvider;

public class InstapayUser {
    private String username;
    private String password;
    private String phone;
    private MoneyProvider moneyProvider;
    private String accountNumber;

    public InstapayUser() {
    }
    public InstapayUser(String username, String password, String phone, MoneyProvider moneyProvider, String accountNumber) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.moneyProvider = moneyProvider;
        this.accountNumber = accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public MoneyProvider getMoneyProvider() {
        return moneyProvider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "InstapayUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", moneyProvider=" + moneyProvider +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
