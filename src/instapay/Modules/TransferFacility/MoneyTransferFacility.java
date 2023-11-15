package instapay.Modules.TransferFacility;

import instapay.Enums.BillsEnum;
import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.Endpoints.BillingEndpoint;
import instapay.Modules.Endpoints.ProviderEndpoint;
import instapay.Modules.Repositories.UserRepository;
import instapay.Modules.Response.Response;
import instapay.Modules.User.User;
import instapay.Modules.Repositories.InMemoryUserRepository;
import instapay.Enums.MoneyProvider;

import java.util.Optional;

public abstract class MoneyTransferFacility {
    protected final UserRepository userRepository = new InMemoryUserRepository();
    protected abstract BillingEndpoint CreateBillingEndpoint(BillsEnum billType);

    protected abstract ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider);

    // This must be an atomic operation by the way. Not our concern right now.
    public Response TransferMoney(String senderProviderAccountIdentifier, String receiverProviderAccountIdentifier
            , MoneyProvider receiverProvider, double amount) {
        Optional<User> senderOptional = userRepository.getUserByProviderAccountIdentifier(senderProviderAccountIdentifier);
        if (senderOptional.isEmpty()) {
            return new Response(false, String.format("Account: %s was not found.", senderProviderAccountIdentifier));
        }

        ProviderEndpoint senderEndpoint = CreateProviderEndpoint(senderOptional.get().getMoneyProvider());

        Response response = senderEndpoint.Debit(senderProviderAccountIdentifier, amount);
        if (!response.succeeded()) {
            return response;
        }

        ProviderEndpoint receiverEndpoint = CreateProviderEndpoint(receiverProvider);

        response = receiverEndpoint.Credit(receiverProviderAccountIdentifier, amount);
        if (!response.succeeded()) {
            // Retract the debit.
            senderEndpoint.Credit(senderProviderAccountIdentifier, amount);

            return response;
        }

        return new Response(true);
    }

    public Response TransferMoneyToInstapay(String senderProviderAccountIdentifier, String receiverUsername, double amount) {
        Optional<User> receiverOptional = userRepository.getUserByUsername(receiverUsername);
        if (receiverOptional.isEmpty()) {
            return new Response(false, String.format("No Instapay user with the username: %s", receiverUsername));
        }

        User receiver = receiverOptional.get();

        return TransferMoney(senderProviderAccountIdentifier, receiver.getProviderAccountIdentifier(), receiver.getMoneyProvider(), amount);
    }

    public Response InquireBalance(String providerAccountIdentifier) {
        Optional<User> userOptional = userRepository.getUserByProviderAccountIdentifier(providerAccountIdentifier);
        if (userOptional.isEmpty()) {
            return new Response(false, String.format("Account: %s was not found.", providerAccountIdentifier));
        }

        ProviderEndpoint endpoint = CreateProviderEndpoint(userOptional.get().getMoneyProvider());

        return endpoint.GetBalance(providerAccountIdentifier);
    }

    public Response GetBill(int billId, BillsEnum billType) {
        return CreateBillingEndpoint(billType).getBill(billId);
    }

    public Response PayBill(String providerAccountIdentifier, int billId, BillsEnum billType) {
        BillingEndpoint billingEndpoint = CreateBillingEndpoint(billType);

        Response response = billingEndpoint.getBill(billId);
        if (!response.succeeded()) {
            return response;
        }

        UtilityBill billToPay = response.to(UtilityBill.class);

        // Get user from Repo (receiver).
        Optional<User> userOptional = userRepository.getUserByProviderAccountIdentifier(providerAccountIdentifier);
        if (userOptional.isEmpty()) {
            return new Response(false, String.format("Account: %s was not found.", providerAccountIdentifier));
        }

        ProviderEndpoint payerEndpoint = CreateProviderEndpoint(userOptional.get().getMoneyProvider());

        response = payerEndpoint.Debit(providerAccountIdentifier, billToPay.getBillAmount());
        if (!response.succeeded()) {
            return response;
        }

        response = billingEndpoint.payBill(billId);

        if (!response.succeeded()) {
            // Retract the debit.
            payerEndpoint.Credit(providerAccountIdentifier, billToPay.getBillAmount());

            return response;
        }

        return new Response(true);
    }

    public Response VerifyAccount(MoneyProvider provider, String providerAccountIdentifier) {
        ProviderEndpoint providerEndpoint = CreateProviderEndpoint(provider);
        return providerEndpoint.VerifyAccount(providerAccountIdentifier);
    }

    // TODO - Expose new method from TransferFacility : VerifyAccount(sameParam, sameParam, phoneNumber)
}
