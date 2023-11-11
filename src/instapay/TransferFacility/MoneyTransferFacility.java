package instapay.TransferFacility;

import instapay.Abstractions.BillingEndpoint;
import instapay.Abstractions.ProviderEndpoint;
import instapay.DataAccess.Models.Bill;
import instapay.Enums.BillingEntity;
import instapay.Enums.MoneyProvider;

public abstract class MoneyTransferFacility {

    public abstract ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider);

    public abstract BillingEndpoint CreateBillingEndpoint(BillingEntity entity);

    // This must be an atomic operation by the way. Not our concern right now.
    public boolean TransferMoney(String senderAccountId, String receiverAccountId, int amount) {
        // Get user from Repo (sender).
        MoneyProvider provider = MoneyProvider.CIB; // Get from user object.
        ProviderEndpoint senderEndpoint = CreateProviderEndpoint(provider);
        if (!senderEndpoint.HasEnoughBalance(senderAccountId, amount)) {
            return false; // Indicate error
        }

        senderEndpoint.Debit(senderAccountId, amount);

        // Get user from Repo (receiver).
        provider = MoneyProvider.Fawry; // Get from user object.
        ProviderEndpoint receiverEndpoint = CreateProviderEndpoint(provider);
        receiverEndpoint.Credit(receiverAccountId, amount);

        return true;
    }

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

        if (!payerEndpoint.HasEnoughBalance(accountId, billToPay.GetAmount())) {
            return false; // Indicate error.
        }

        BillingEndpoint billingEndpoint = CreateBillingEndpoint(entity);
        payerEndpoint.Debit(accountId, billToPay.GetAmount());
        billingEndpoint.PayBill(billId);

        return true;
    }
}
