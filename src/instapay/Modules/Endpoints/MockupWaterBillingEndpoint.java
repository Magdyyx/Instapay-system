package instapay.Modules.Endpoints;

import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.Response.Response;

public class MockupWaterBillingEndpoint extends BillingEndpoint {
    @Override
    public Response getBill(int billId) {
        UtilityBill bill = billRepository.getBill(billId);

        if (bill == null) {
            return new Response(false, "Bill not found");
        }

        return new Response(true, bill);
    }

    @Override
    public Response payBill(int billId) {
        UtilityBill bill = billRepository.getBill(billId);

        if (bill == null) {
            return new Response(false, "Bill not found");
        }

        bill.payBill();
        billRepository.updateBill(bill);

        return new Response(true, bill);
    }
}
