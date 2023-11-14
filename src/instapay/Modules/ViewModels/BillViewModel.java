package instapay.Modules.ViewModels;

import instapay.Enums.BillsEnum;

public class BillViewModel {
    private int billId;
    private BillsEnum type;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public BillsEnum getType() {
        return type;
    }

    public void setType(BillsEnum type) {
        this.type = type;
    }
}
