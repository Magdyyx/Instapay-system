package instapay.Modules.Endpoints;

import instapay.Modules.Bill.UtilityBill;

public class MockupElectricityBillingEndpoint extends BillingEndpoint {
    @Override
    public UtilityBill getBill(int billId) {
        return billRepository.getBill(billId);
    }

    @Override
    public boolean payBill(int billId) {
        UtilityBill bill = billRepository.getBill(billId);

        bill.payBill();
        billRepository.updateBill(bill);

        return true;
    }
}
