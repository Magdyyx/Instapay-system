package instapay.Modules.Endpoints;

import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.Repositories.BillRepository;
import instapay.Modules.Repositories.InMemoryBillRepository;

public abstract class BillingEndpoint {
    protected static final BillRepository billRepository = new InMemoryBillRepository();

    public abstract UtilityBill getBill(int billId);

    public abstract boolean payBill(int billId);
}
