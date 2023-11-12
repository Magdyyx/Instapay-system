package instapay.Modules.BillPayment;

public class BillPaymentService {
    public void payBill(Bill bill) {
        // Logic for paying the bill
        System.out.println("Paying " + bill.getType() + " bill of amount " + bill.getAmount());
        // Additional payment logic, such as deducting the amount from the user's account
    }
}
