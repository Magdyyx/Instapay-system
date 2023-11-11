package instapay.Model.UtilityBillsModels;

public class ElectricityBill extends UtilityBill {
    public ElectricityBill(int userId) {
        super(userId);
    }

    public ElectricityBill(int billId, int userId, boolean isPaid, float billAmount) {
        super(billId, userId, isPaid, billAmount);
    }

    @Override
    public String details() {
        return "";
    }
}
