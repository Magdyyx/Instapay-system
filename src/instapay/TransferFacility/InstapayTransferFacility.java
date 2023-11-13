package instapay.TransferFacility;

import instapay.Endpoints.MockupBillingEndpoint;
import instapay.Abstractions.ProviderEndpoint;
import instapay.Endpoints.*;
import instapay.Enums.BillsEnum;
import instapay.Enums.MoneyProvider;

public class InstapayTransferFacility extends MoneyTransferFacility {

    @Override
    public ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider) {
        switch (provider) {
            case Fawry:
                return new MockupFawryEndpoint();
            case CIB:
                return new MockupCIBEndpoint();
            case Alahly:
                return new MockupAlahlyEndpoint();
            default:
                return null;
        }
    }
}
