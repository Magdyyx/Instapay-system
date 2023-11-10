package instapay.Model.UtilityBillsModels;

public class GasBill extends UtilityBill {
    public GasBill(int userId) {
        super(userId);
    }

    public GasBill(int billId, int userId, boolean isPaid, float billAmount) {
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
