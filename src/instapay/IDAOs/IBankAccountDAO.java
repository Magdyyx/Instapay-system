package instapay.IDAOs;

import instapay.DataAccess.Models.BankAccount;

public interface IBankAccountDAO {
    BankAccount getBankAccountByUserId(int userId);
    void addBankAccount(BankAccount bankAccount);
    void updateBankAccount(BankAccount bankAccount);
    void deleteBankAccount(int userId);
}
