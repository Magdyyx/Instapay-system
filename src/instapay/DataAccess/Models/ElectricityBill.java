package instapay.DataAccess.Models;

import instapay.Abstractions.UtilityBill;

public class ElectricityBill extends UtilityBill {
    public ElectricityBill(int userId) {
        super(userId);
    }

    public ElectricityBill(int billId, int userId, boolean isPaid, float billAmount) {
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
