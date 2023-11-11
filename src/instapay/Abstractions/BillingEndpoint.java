package instapay.Abstractions;

import instapay.DataAccess.Models.Bill;

public abstract class BillingEndpoint {
    public abstract Bill GetBill(String billId);

    public abstract boolean PayBill(String billId);
}
