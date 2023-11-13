package instapay.Modules.TransferFacility;

import instapay.Modules.Endpoints.ProviderEndpoint;
import instapay.Modules.Endpoints.*;
import instapay.Enums.MoneyProvider;

public class InstapayTransferFacility extends MoneyTransferFacility {

    @Override
    public ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider) {
        switch (provider) {
            case Fawry:
                return new MockupFawryEndpoint();
            case CIBBank:
                return new MockupCIBEndpoint();
            case Alahly:
                return new MockupAlahlyEndpoint();
            default:
                return null;
        }
    }
}
