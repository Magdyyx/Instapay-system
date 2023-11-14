package instapay.Modules.User;

import instapay.Enums.MoneyProvider;
import instapay.Modules.Account.AccountInfo;
import instapay.Modules.Account.BankAccountInfo;

public class User {
    private String username;
    private String password;
    private String phone;
    private MoneyProvider moneyProvider;
    private String providerAccountIdentifier;
    private AccountInfo accountInfo;

    public User() {
    }
    public User(String username, String password, String phone, MoneyProvider moneyProvider, String providerAccountIdentifier) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.moneyProvider = moneyProvider;
        this.providerAccountIdentifier = providerAccountIdentifier;
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

    public String getProviderAccountIdentifier() {
        return providerAccountIdentifier;
    }

    public boolean accountIsBank() {
        return accountInfo instanceof BankAccountInfo;
    }

    @Override
    public String toString() {
        return String.format("Username: %-15s | Phone number: %-15s | Account: %-10s %s",
                username, phone, moneyProvider.toString(), providerAccountIdentifier);
    }
}
