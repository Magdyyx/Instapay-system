package instapay.Modules.Bill;

public class GasBill extends UtilityBill {
    public GasBill(int userId) {
        super(userId);
    }

    public GasBill(int billId, int userId, boolean isPaid, float billAmount) {
        super(billId, userId, isPaid, billAmount);
    }


    @Override
    public String details() {
        return "";
    }
}
