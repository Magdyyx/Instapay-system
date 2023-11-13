package instapay.Modules.Endpoints;

import instapay.Modules.Bill.UtilityBill;

public interface BillingEndpoint {
    public UtilityBill getBill(int billId);

    public boolean payBill(int billId);
}
