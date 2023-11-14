package instapay.Modules.TransferFacility;

import instapay.Enums.BillsEnum;
import instapay.Modules.Endpoints.ProviderEndpoint;
import instapay.Modules.Endpoints.*;
import instapay.Enums.MoneyProvider;

public class InstapayTransferFacility extends MoneyTransferFacility {

    @Override
    protected BillingEndpoint CreateBillingEndpoint(BillsEnum billType) {
        switch (billType) {
            case Gas:
                return new MockupGasBillingEndpoint();
            case Water:
                return new MockupWaterBillingEndpoint();
            case Electricity:
                return new MockupElectricityBillingEndpoint();
            default:
                return null;
        }
    }

    @Override
    protected ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider) {
        switch (provider) {
            case Fawry:
                return new MockupFawryEndpoint();
            case CIBBank:
                return new MockupCIBEndpoint();
            case Alahly:
                return new MockupAlahlyEndpoint();
            case VCash:
                return new MockupVCashEndpoint();
            default:
                return null;
        }
    }
}
