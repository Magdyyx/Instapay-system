package instapay.Modules.Repositories;

import instapay.Enums.MoneyProvider;
import instapay.Modules.Account.ExternalAccount;
import java.util.Optional;

public interface AccountRepository {

    public void addAccount(ExternalAccount account);

    public void updateAccount(ExternalAccount account);

    public Optional<ExternalAccount> getAccountBy(String providerAccountIdentifier, MoneyProvider provider);
}
