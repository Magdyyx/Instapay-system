package instapay.Modules.Endpoints;

import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.Repositories.BillRepository;
import instapay.Modules.Repositories.InMemoryBillRepository;

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
