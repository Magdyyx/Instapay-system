package instapay.Model.UtilityBillsModels;

import java.util.Random;

public abstract class UtilityBill {
    private int billId;
    private int userId;
    private boolean isPaid;
    private float billAmount;

    public UtilityBill(int userId) {
        this.userId = userId;
        this.isPaid = false;
        this.billAmount = new Random().nextFloat(1, 1000);
    }

    public UtilityBill(int billId, int userId, boolean isPaid, float billAmount) {
        this(userId);
        this.billId = billId;
        this.isPaid = isPaid;
        this.billAmount = billAmount;
    }

    public abstract void AddBillDB();
    public abstract void UpdateBillDB();
    public abstract void DeleteBillDB();
    public abstract String details();

    public int getBillId() {
        return billId;
    }
    public int getUserId() {
        return userId;
    }
    public float getBillAmount() {
        return billAmount;
    }
    public boolean IsPaid() {
        return isPaid;
    }

    public void payBill() {
        isPaid = true;

        UpdateBillDB();
    }
}
