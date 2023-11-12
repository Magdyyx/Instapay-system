package instapay.Modules.TransferStrategy;
import instapay.Modules.User.User;
import instapay.Modules.Providers.ProviderEndpoint;
public class MoneyTransferFacility {

    //public abstract ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider);

    //public abstract BillingEndpoint CreateBillingEndpoint(BillingEntity entity);

    // This must be an atomic operation by the way. Not our concern right now.
    public boolean TransferMoney(User senderAccount, User receiverAccount, int amount) {

        if (senderAccount.getUserType().equals("wallet") && receiverAccount.getUserType().equals("bank")){
            return false; // Indicate error
        } else {
            ProviderEndpoint senderEndpoint = senderAccount.getMoneyProvider();
            if (!senderEndpoint.HasEnoughBalance(amount)) {
                return false; // Indicate error
            }
            senderEndpoint.withdraw(amount);

            ProviderEndpoint receiverEndpoint = receiverAccount.getMoneyProvider();
            receiverEndpoint.deposit(amount);
            return true;
        }


    }
    public double InquireBalance(User user) {
        ProviderEndpoint endpoint = user.getMoneyProvider();
        return endpoint.getBalance();
    }


/*
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

    }*/
}
