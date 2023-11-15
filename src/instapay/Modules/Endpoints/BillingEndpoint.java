package instapay.Modules.Endpoints;

import instapay.Modules.Repositories.BillRepository;
import instapay.Modules.Repositories.InMemoryBillRepository;
import instapay.Modules.Response.Response;

public abstract class BillingEndpoint {
    protected static final BillRepository billRepository = new InMemoryBillRepository();

    public abstract Response getBill(int billId);

    public abstract Response payBill(int billId);
}
