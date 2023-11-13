package instapay.Modules.Bill;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import instapay.Enums.BillsEnum;
import instapay.Modules.Bill.ElectricityBill;
import instapay.Modules.Bill.GasBill;
import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.Bill.WaterBill;

public class UtilityBillFactory {
    private Map<BillsEnum, Function<Integer, UtilityBill>> stateDictionary = new HashMap<>();

    public UtilityBillFactory() {
        stateDictionary.put(BillsEnum.Gas, (userId) -> new GasBill(userId));
        stateDictionary.put(BillsEnum.Water, (userId) -> new WaterBill(userId));
        stateDictionary.put(BillsEnum.Electricity, (userId) -> new ElectricityBill(userId));
    }

    public UtilityBill createRandomBill(int userId) {
        BillsEnum[] billTypes = BillsEnum.values();
        int randomBill = new Random().nextInt(billTypes.length);

        return stateDictionary.get(billTypes[randomBill]).apply(userId);
    }
}
