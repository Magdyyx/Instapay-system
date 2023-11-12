// TODO this class should be deleted and use MoneyTransferFacility instead
// we just do transfer(user1, user2, amount) regardless of the type of account in MoneyTransferFacility
package instapay.Modules.TransferStrategy;

public interface TransferStrategy {
    void transferToAnotherInstapayAccount();
    void transferToBankAccount();
    void transferToMobileWallet();
}