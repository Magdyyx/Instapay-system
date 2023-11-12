package instapay.DataAccess.Models;

import instapay.Abstractions.MoneyAccount;

public class BankAccount implements MoneyAccount {
    private int accountNumber;
    private int userId;
    private String bankName;

    public BankAccount(int accountNumber, int userId, String bankName) {
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.bankName = bankName;
    }
    public int getAccountNumber() {
        return this.accountNumber;
    }
    public int getUserId() {
        return this.userId;
    }
    public String getBankName() {
        return this.bankName;
    }

    @Override
    public String getAccountProvider() {
        return this.bankName;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
