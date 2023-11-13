package instapay.TransferFacility;

import instapay.Abstractions.BillingEndpoint;
import instapay.Endpoints.MockupBillingEndpoint;
import instapay.Abstractions.ProviderEndpoint;
import instapay.Abstractions.UserRepository;
import instapay.Abstractions.UtilityBill;
import instapay.DataAccess.Models.InstapayUser;
import instapay.DataAccess.Repositories.InMemoryUserRepository;
import instapay.Enums.BillsEnum;
import instapay.Enums.MoneyProvider;

import java.util.Optional;

public abstract class MoneyTransferFacility {
    protected final UserRepository userRepository = new InMemoryUserRepository();
    protected final BillingEndpoint billingEndpoint = new MockupBillingEndpoint();

    public abstract ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider);

    // This must be an atomic operation by the way. Not our concern right now.
    public boolean TransferMoney(String senderAccountNumber, String receiverAccountNumber, int amount) {
        Optional<InstapayUser> senderOptional = userRepository.getUserByAccountNumber(senderAccountNumber);
        Optional<InstapayUser> receiverOptional = userRepository.getUserByAccountNumber(receiverAccountNumber);

        if (senderOptional.isEmpty() || receiverOptional.isEmpty()) {
            return false;
        }

        InstapayUser sender = senderOptional.get();
        InstapayUser receiver = receiverOptional.get();

        ProviderEndpoint senderEndpoint = CreateProviderEndpoint(sender.getMoneyProvider());
        if (!senderEndpoint.HasEnoughBalance(senderAccountNumber, amount)) {
            return false; // Indicate error
        }

        if (!senderEndpoint.Debit(senderAccountNumber, amount)) {
            return false;
        }

        ProviderEndpoint receiverEndpoint = CreateProviderEndpoint(receiver.getMoneyProvider());

        if (!receiverEndpoint.Credit(receiverAccountNumber, amount)) {
            // Retract the debit.
            senderEndpoint.Credit(senderAccountNumber, amount);

            return false;
        }

        return true;
    }

    public boolean TransferMoneyToInstapay(String senderAccountNumber, String receiverUsername, int amount) {
        Optional<InstapayUser> receiverOptional = userRepository.getUserByUsername(receiverUsername);
        if (receiverOptional.isEmpty()) {
            return false; // Receiver not found.
        }

        return TransferMoney(senderAccountNumber, receiverOptional.get().getAccountNumber(), amount);
    }

    public double InquireBalance(String accountNumber) {
        Optional<InstapayUser> userOptional = userRepository.getUserByAccountNumber(accountNumber);
        if (userOptional.isEmpty()) {
            // Indicate error; by throwing exception for example.
            return -1.0;
        }

        ProviderEndpoint endpoint = CreateProviderEndpoint(userOptional.get().getMoneyProvider());

        return endpoint.GetBalance(accountNumber);
    }

    public UtilityBill GetBill(int billId) {
        return billingEndpoint.getBill(billId);
    }

    public boolean PayBill(String accountNumber, int billId) {
        UtilityBill billToPay = GetBill(billId);

        // Get user from Repo (receiver).
        Optional<InstapayUser> userOptional = userRepository.getUserByAccountNumber(accountNumber);
        if (userOptional.isEmpty()) {
            return false;
        }

        ProviderEndpoint payerEndpoint = CreateProviderEndpoint(userOptional.get().getMoneyProvider());

        if (!payerEndpoint.HasEnoughBalance(accountNumber, billToPay.getBillAmount())) {
            return false; // Indicate error.
        }

        if (!payerEndpoint.Debit(accountNumber, billToPay.getBillAmount())) {
            return false;
        }

        if (!billingEndpoint.payBill(billId)) {
            // Retract the debit.
            payerEndpoint.Credit(accountNumber, billToPay.getBillAmount());

            return false;
        }

        return true;
    }

    public boolean VerifyAccount(MoneyProvider provider, String accountNumber) {
        ProviderEndpoint providerEndpoint = CreateProviderEndpoint(provider);
        return providerEndpoint.VerifyAccount(accountNumber);
    }
}
