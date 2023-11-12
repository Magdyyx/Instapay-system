package instapay.Modules.TransferStrategy;

public interface TransferStrategy {
    void transferToAnotherInstapayAccount();
    void transferToBankAccount();
    void transferToMobileWallet();
}