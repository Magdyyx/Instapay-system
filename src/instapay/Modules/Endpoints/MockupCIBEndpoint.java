package instapay.Modules.Endpoints;

import instapay.Enums.MoneyProvider;
import instapay.Modules.Account.ExternalAccount;
import instapay.Modules.Response.Response;

import java.util.Optional;
import java.util.Random;

public class MockupCIBEndpoint extends ProviderEndpoint {
    private static final MoneyProvider PROVIDER = MoneyProvider.CIBBank;

    @Override
    public Response Credit(String providerAccountIdentifier, double amount) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER);
        if (accountOptional.isPresent()) {
            ExternalAccount toUpdate = accountOptional.get();
            toUpdate.setBalance(toUpdate.getBalance() + amount);

            return new Response(true);
        } else {
            return new Response(false, String.format("Account: %s was not found.", providerAccountIdentifier));
        }
    }

    @Override
    public Response Debit(String providerAccountIdentifier, double amount) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER);
        if (accountOptional.isPresent()) {
            ExternalAccount toUpdate = accountOptional.get();

            if (toUpdate.getBalance() - amount < 0.0) {
                return new
                        Response(false,
                        String.format("Account: %s does not have enough balance.", providerAccountIdentifier));
            }

            toUpdate.setBalance(toUpdate.getBalance() - amount);

            return new Response(true);
        } else {
            return new Response(false, String.format("Account: %s was not found.", providerAccountIdentifier));
        }
    }

    @Override
    public Response HasEnoughBalance(String providerAccountIdentifier, double amount) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER);

        if (accountOptional.isEmpty()) {
            return new Response(false, String.format("Account: %s was not found.", providerAccountIdentifier));
        }

        return new Response(true, (accountOptional.get().getBalance() >= amount));
    }

    @Override
    public Response GetBalance(String providerAccountIdentifier) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER);

        if (accountOptional.isEmpty()) {
            return new Response(false, String.format("Account: %s was not found.", providerAccountIdentifier));
        }

        return new Response(true, accountOptional.get().getBalance());
    }

    @Override
    public Response VerifyAccount(String providerAccountIdentifier, String phoneNumber) {
        Optional<ExternalAccount> accountOptional = accountRepository.getAccountBy(providerAccountIdentifier, PROVIDER);

        if (phoneNumber != null /* is in the company's database*/) {
            // true;
        }

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        return new Response(accountOptional.isPresent(), otp);
    }

}
