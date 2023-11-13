package instapay.Modules.Repositories;

public interface BillRepository {
    public UtilityBill getBill(int billId);

    public void updateBill(UtilityBill bill);
}
