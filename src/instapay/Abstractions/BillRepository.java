package instapay.Abstractions;

public interface BillRepository {
    public UtilityBill getBill(int billId);

    public void updateBill(UtilityBill bill);
}
