package instapay.Modules.User;

import instapay.Enums.MoneyProvider;
import instapay.Modules.Account.AccountInfo;

public class InstapayUser {
    private String username;
    private String password;
    private String phone;
    private MoneyProvider moneyProvider;
    private String providerHandle;
    private AccountInfo accountInfo;

    public InstapayUser() {
    }
    public InstapayUser(String username, String password, String phone, MoneyProvider moneyProvider, String providerHandle) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.moneyProvider = moneyProvider;
        this.providerHandle = providerHandle;
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

    public String getProviderHandle() {
        return providerHandle;
    }

    @Override
    public String toString() {
        return "InstapayUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", moneyProvider=" + moneyProvider +
                ", accountNumber='" + providerHandle + '\'' +
                '}';
    }
}
