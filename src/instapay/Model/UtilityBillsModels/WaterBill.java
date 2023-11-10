package instapay.Model.UtilityBillsModels;

public class WaterBill extends UtilityBill {
    public WaterBill(int userId) {
        super(userId);
    }

    public WaterBill(int billId, int userId, boolean isPaid, float billAmount) {
        super(billId, userId, isPaid, billAmount);
    }

    @Override
    public void AddBillDB() {
        return;
    }

    @Override
    public void UpdateBillDB() {
        return;
    }

    @Override
    public void DeleteBillDB() {
        return;
    }

    @Override
    public String details() {
        return "";
    }
}
