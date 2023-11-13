package instapay.Modules.Endpoints;

import instapay.Model.UtilityBillsModels.UtilityBill;

public interface BillingEndpoint {
    public UtilityBill getBill(int billId);

    public boolean payBill(int billId);
}
