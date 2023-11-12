package instapay.Modules.BillPayment;

public class Bill {
    private String type;
    private double amount;

    // Constructor
    public Bill(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}

