package instapay.Modules.TransferFacility;

import instapay.Enums.BillsEnum;
import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.Endpoints.BillingEndpoint;
import instapay.Modules.Endpoints.ProviderEndpoint;
import instapay.Modules.Repositories.UserRepository;
import instapay.Modules.User.User;
import instapay.Modules.Repositories.InMemoryUserRepository;
import instapay.Enums.MoneyProvider;

import java.util.Optional;

public abstract class MoneyTransferFacility {
    protected final UserRepository userRepository = new InMemoryUserRepository();
    protected abstract BillingEndpoint CreateBillingEndpoint(BillsEnum billType);

    protected abstract ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider);

    // This must be an atomic operation by the way. Not our concern right now.
    public boolean TransferMoney(String senderProviderAccountIdentifier, String receiverProviderAccountIdentifier
            , MoneyProvider receiverProvider, int amount) {
        Optional<User> senderOptional = userRepository.getUserByProviderAccountIdentifier(senderProviderAccountIdentifier);
        if (senderOptional.isEmpty()) {
            return false;
        }

        ProviderEndpoint senderEndpoint = CreateProviderEndpoint(senderOptional.get().getMoneyProvider());
        if (!senderEndpoint.HasEnoughBalance(senderProviderAccountIdentifier, amount)) {
            return false; // Indicate error
        }

        if (!senderEndpoint.Debit(senderProviderAccountIdentifier, amount)) {
            return false;
        }

        ProviderEndpoint receiverEndpoint = CreateProviderEndpoint(receiverProvider);

        if (!receiverEndpoint.Credit(receiverProviderAccountIdentifier, amount)) {
            // Retract the debit.
            senderEndpoint.Credit(senderProviderAccountIdentifier, amount);

            return false;
        }

        return true;
    }

    public boolean TransferMoney(String senderProviderAccountIdentifier, String receiverproviderAccountIdentifier, int amount) {
        Optional<User> receiverOptional = userRepository.getUserByProviderAccountIdentifier(receiverproviderAccountIdentifier);

        if (receiverOptional.isEmpty()) {
            return false;
        }

        User receiver = receiverOptional.get();

        return TransferMoney(senderProviderAccountIdentifier, receiverproviderAccountIdentifier, receiver.getMoneyProvider(), amount);
    }

    public boolean TransferMoneyToInstapay(String senderProviderAccountIdentifier, String receiverUsername, int amount) {
        Optional<User> receiverOptional = userRepository.getUserByUsername(receiverUsername);
        if (receiverOptional.isEmpty()) {
            return false; // Receiver not found.
        }

        return TransferMoney(senderProviderAccountIdentifier, receiverOptional.get().getProviderAccountIdentifier(), amount);
    }

    public double InquireBalance(String providerAccountIdentifier) {
        Optional<User> userOptional = userRepository.getUserByProviderAccountIdentifier(providerAccountIdentifier);
        if (userOptional.isEmpty()) {
            // Indicate error; by throwing exception for example.
            return -1.0;
        }

        ProviderEndpoint endpoint = CreateProviderEndpoint(userOptional.get().getMoneyProvider());

        return endpoint.GetBalance(providerAccountIdentifier);
    }

    public UtilityBill GetBill(int billId, BillsEnum billType) {
        return CreateBillingEndpoint(billType).getBill(billId);
    }

    public boolean PayBill(String providerAccountIdentifier, int billId, BillsEnum billType) {
        BillingEndpoint billingEndpoint = CreateBillingEndpoint(billType);

        UtilityBill billToPay = billingEndpoint.getBill(billId);

        // Get user from Repo (receiver).
        Optional<User> userOptional = userRepository.getUserByProviderAccountIdentifier(providerAccountIdentifier);
        if (userOptional.isEmpty()) {
            return false;
        }

        ProviderEndpoint payerEndpoint = CreateProviderEndpoint(userOptional.get().getMoneyProvider());

        if (!payerEndpoint.HasEnoughBalance(providerAccountIdentifier, billToPay.getBillAmount())) {
            return false; // Indicate error.
        }

        if (!payerEndpoint.Debit(providerAccountIdentifier, billToPay.getBillAmount())) {
            return false;
        }

        if (!billingEndpoint.payBill(billId)) {
            // Retract the debit.
            payerEndpoint.Credit(providerAccountIdentifier, billToPay.getBillAmount());

            return false;
        }

        return true;
    }

    public boolean VerifyAccount(MoneyProvider provider, String providerAccountIdentifier) {
        ProviderEndpoint providerEndpoint = CreateProviderEndpoint(provider);
        return providerEndpoint.VerifyAccount(providerAccountIdentifier);
    }
}
