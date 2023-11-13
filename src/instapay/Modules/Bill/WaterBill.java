package instapay.Modules.Bill;

public class WaterBill extends UtilityBill {
    public WaterBill(int userId) {
        super(userId);
    }

    public WaterBill(int billId, int userId, boolean isPaid, float billAmount) {
        super(billId, userId, isPaid, billAmount);
    }

    @Override
    public String details() {
        return "";
    }
}
