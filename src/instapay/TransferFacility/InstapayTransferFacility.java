package instapay.TransferFacility;

import instapay.Abstractions.BillingEndpoint;
import instapay.Abstractions.ProviderEndpoint;
import instapay.Endpoints.*;
import instapay.Enums.BillingEntity;
import instapay.Enums.MoneyProvider;

public class InstapayTransferFacility extends MoneyTransferFacility {

    @Override
    public ProviderEndpoint CreateProviderEndpoint(MoneyProvider provider) {
        switch (provider) {
            case Fawry:
                return new FawryEndpoint();
            case CIB:
                return new CIBEndpoint();
            case Alahly:
                return new AlahlyEndpoint();
            default:
                return null;
        }
    }

    @Override
    public BillingEndpoint CreateBillingEndpoint(BillingEntity entity) {
        switch (entity) {
            case Electricity:
                return new ElectricityBillingEndpoint();
            case Water:
                return new WaterBillingEndpoint();
            case Gas:
                return new GasBillingEndpoint();

            default:
                return null;
        }
    }
}
