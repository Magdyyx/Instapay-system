package instapay.Modules.Repositories;

import instapay.Modules.Bill.UtilityBill;

public interface BillRepository {
    public UtilityBill getBill(int billId);

    public void updateBill(UtilityBill bill);
}
