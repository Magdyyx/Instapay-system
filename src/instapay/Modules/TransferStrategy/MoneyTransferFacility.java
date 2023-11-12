package instapay.Modules.TransferStrategy;
import instapay.Modules.User.User;
import instapay.Modules.Providers.ProviderEndpoint;
public class MoneyTransferFacility {

    //public abstract ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider);

    //public abstract BillingEndpoint CreateBillingEndpoint(BillingEntity entity);

    // This must be an atomic operation by the way. Not our concern right now.
    public boolean TransferMoney(User senderAccount, User receiverAccount, int amount) {
        // Get user from Repo (sender).
        // MoneyProvider provider = findEnumByString(senderAccount.getMoneyProviderName()); // Get from user object.
        ProviderEndpoint senderEndpoint = senderAccount.getMoneyProvider();
        //ProviderEndpoint senderEndpoint = CreateProviderEndpoint(provider);
        if (!senderEndpoint.HasEnoughBalance(amount)) {
            return false; // Indicate error
        }

        senderEndpoint.Debit(amount);

        // Get user from Repo (receiver).
        //provider = receiverAccount.getMoneyProvider(); // Get from user object.
        ProviderEndpoint receiverEndpoint = receiverAccount.getMoneyProvider();
        receiverEndpoint.Credit(amount);
        return true;
    }
    /*

    public int InquireBalance(String accountId) {
        // Get user from Repo (sender).
        MoneyProvider provider = MoneyProvider.CIB; // Get from user object.
        ProviderEndpoint endpoint = CreateProviderEndpoint(provider);

        return endpoint.GetBalance(accountId);
    }

    public Bill GetBill(BillingEntity entity, String billId) {
        BillingEndpoint endpoint = CreateBillingEndpoint(entity);
        return endpoint.GetBill(billId);
    }

    public boolean PayBill(BillingEntity entity, String accountId, String billId) {
        Bill billToPay = GetBill(entity, billId);

        // Get user from Repo (receiver).
        MoneyProvider provider = MoneyProvider.Fawry; // Get from user object.
        ProviderEndpoint payerEndpoint = CreateProviderEndpoint(provider);

        if (!payerEndpoint.HasEnoughBalance(billToPay.GetAmount())) {
            return false; // Indicate error.
        }

        BillingEndpoint billingEndpoint = CreateBillingEndpoint(entity);
        payerEndpoint.Debit(accountId, billToPay.GetAmount());
        billingEndpoint.PayBill(billId);

        return true;
    }

    // funtion to get the matching MoneyProvider from the enum
    public MoneyProvider findEnumByString(String input) {
        for (MoneyProvider value : MoneyProvider.values()) {
            if (value.name().equals(input)) {
                return value;
            }
        }
        return null;
    }*/
}
