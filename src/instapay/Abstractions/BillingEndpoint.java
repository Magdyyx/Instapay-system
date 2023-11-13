package instapay.Abstractions;

public interface BillingEndpoint {
    public UtilityBill getBill(int billId);

    public boolean payBill(int billId);
}
