package instapay.Modules.BillPayment;

public class BillFactory {
    public static Bill createBill(String type, double amount) {
        switch (type.toLowerCase()) {
            case "gas":
                return new GasBill(type, amount);
            case "electricity":
                return new ElectricityBill(type, amount);
            case "water":
                return new WaterBill(type, amount);
            default:
                throw new IllegalArgumentException("Invalid bill type: " + type);
        }
    }
}