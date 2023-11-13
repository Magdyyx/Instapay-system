package instapay.Abstractions;

import instapay.DataAccess.Models.ExternalAccount;
import java.util.Optional;

public interface AccountRepository {

    public void addAccount(ExternalAccount account);

    public void updateAccount(ExternalAccount account);

    public Optional<ExternalAccount> getAccountBy(String accountNumber);
}
