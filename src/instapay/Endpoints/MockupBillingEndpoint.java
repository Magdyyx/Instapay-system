package instapay.Endpoints;

import instapay.Abstractions.BillRepository;
import instapay.Abstractions.BillingEndpoint;
import instapay.Abstractions.UtilityBill;
import instapay.DataAccess.Repositories.InMemoryBillRepository;

public class MockupBillingEndpoint implements BillingEndpoint {
    private final BillRepository billRepository = new InMemoryBillRepository();

    public UtilityBill getBill(int billId) {
        return billRepository.getBill(billId);
    }

    public boolean payBill(int billId) {
        UtilityBill bill = billRepository.getBill(billId);

        bill.payBill();
        billRepository.updateBill(bill);

        return true;
    }
}
