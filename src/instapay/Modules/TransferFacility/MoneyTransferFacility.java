package instapay.Modules.TransferFacility;

import instapay.Model.UtilityBillsModels.UtilityBill;
import instapay.Modules.Endpoints.BillingEndpoint;
import instapay.Modules.Endpoints.MockupBillingEndpoint;
import instapay.Modules.Endpoints.ProviderEndpoint;
import instapay.Modules.Repositories.UserRepository;
import instapay.Modules.User.InstapayUser;
import instapay.Modules.Repositories.InMemoryUserRepository;
import instapay.Enums.MoneyProvider;

import java.util.Optional;

public abstract class MoneyTransferFacility {
    protected final UserRepository userRepository = new InMemoryUserRepository();
    protected final BillingEndpoint billingEndpoint = new MockupBillingEndpoint();

    public abstract ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider);

    // This must be an atomic operation by the way. Not our concern right now.
    public boolean TransferMoney(String senderProviderHandle, String receiverProviderHandle
            , MoneyProvider receiverProvider, int amount) {
        Optional<InstapayUser> senderOptional = userRepository.getUserByProviderHandle(senderProviderHandle);
        if (senderOptional.isEmpty()) {
            return false;
        }

        ProviderEndpoint senderEndpoint = CreateProviderEndpoint(senderOptional.get().getMoneyProvider());
        if (!senderEndpoint.HasEnoughBalance(senderProviderHandle, amount)) {
            return false; // Indicate error
        }

        if (!senderEndpoint.Debit(senderProviderHandle, amount)) {
            return false;
        }

        ProviderEndpoint receiverEndpoint = CreateProviderEndpoint(receiverProvider);

        if (!receiverEndpoint.Credit(receiverProviderHandle, amount)) {
            // Retract the debit.
            senderEndpoint.Credit(senderProviderHandle, amount);

            return false;
        }

        return true;
    }

    public boolean TransferMoney(String senderProviderHandle, String receiverProviderHandle, int amount) {
        Optional<InstapayUser> receiverOptional = userRepository.getUserByProviderHandle(receiverProviderHandle);

        if (receiverOptional.isEmpty()) {
            return false;
        }

        InstapayUser receiver = receiverOptional.get();

        return TransferMoney(senderProviderHandle, receiverProviderHandle, receiver.getMoneyProvider(), amount);
    }

    public boolean TransferMoneyToInstapay(String senderProviderHandle, String receiverUsername, int amount) {
        Optional<InstapayUser> receiverOptional = userRepository.getUserByUsername(receiverUsername);
        if (receiverOptional.isEmpty()) {
            return false; // Receiver not found.
        }

        return TransferMoney(senderProviderHandle, receiverOptional.get().getProviderHandle(), amount);
    }

    public double InquireBalance(String providerHandle) {
        Optional<InstapayUser> userOptional = userRepository.getUserByProviderHandle(providerHandle);
        if (userOptional.isEmpty()) {
            // Indicate error; by throwing exception for example.
            return -1.0;
        }

        ProviderEndpoint endpoint = CreateProviderEndpoint(userOptional.get().getMoneyProvider());

        return endpoint.GetBalance(providerHandle);
    }

    public UtilityBill GetBill(int billId) {
        return billingEndpoint.getBill(billId);
    }

    public boolean PayBill(String providerHandle, int billId) {
        UtilityBill billToPay = GetBill(billId);

        // Get user from Repo (receiver).
        Optional<InstapayUser> userOptional = userRepository.getUserByProviderHandle(providerHandle);
        if (userOptional.isEmpty()) {
            return false;
        }

        ProviderEndpoint payerEndpoint = CreateProviderEndpoint(userOptional.get().getMoneyProvider());

        if (!payerEndpoint.HasEnoughBalance(providerHandle, billToPay.getBillAmount())) {
            return false; // Indicate error.
        }

        if (!payerEndpoint.Debit(providerHandle, billToPay.getBillAmount())) {
            return false;
        }

        if (!billingEndpoint.payBill(billId)) {
            // Retract the debit.
            payerEndpoint.Credit(providerHandle, billToPay.getBillAmount());

            return false;
        }

        return true;
    }

    public boolean VerifyAccount(MoneyProvider provider, String providerHandle) {
        ProviderEndpoint providerEndpoint = CreateProviderEndpoint(provider);
        return providerEndpoint.VerifyAccount(providerHandle);
    }
}
