package instapay.IDAOs;
import instapay.DataAccess.Models.WalletProvider;
public interface IWalletProviderDAO {
    WalletProvider getWalletProviderByUserId(int userId);
    void addWalletProvider(WalletProvider walletProvider);
    void updateWalletProvider(WalletProvider walletProvider);
    void deleteWalletProvider(int userId);
}