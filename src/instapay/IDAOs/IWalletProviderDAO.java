package instapay.IDAOs;

public interface IWalletProviderDAO {
    WalletProvider getWalletProviderByUserId(int userId);
    void addWalletProvider(WalletProvider walletProvider);
    void updateWalletProvider(WalletProvider walletProvider);
    void deleteWalletProvider(int userId);
}