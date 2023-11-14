package instapay.Modules.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import instapay.Modules.Bill.ElectricityBill;
import instapay.Modules.Bill.GasBill;
import instapay.Modules.Bill.UtilityBill;
import instapay.Modules.Bill.WaterBill;

public class InMemoryBillRepository implements BillRepository {

    private static final List<UtilityBill> bills = init();

    private static List<UtilityBill> init() {
        ArrayList<UtilityBill> bills = new ArrayList<>();
        bills.add(new ElectricityBill(1, 0, false, 490));
        bills.add(new GasBill(2, 0, false, 120));
        bills.add(new WaterBill(3, 0, false, 6894));

        return bills;
    }

    @Override
    public UtilityBill getBill(int billId) {
        Optional<UtilityBill> billOptional = bills.stream()
                .filter(b -> b.getBillId() == billId)
                .findFirst();

        return billOptional.orElse(null);
    }

    @Override
    public void updateBill(UtilityBill bill) {
        Optional<UtilityBill> updateCandidate = bills.stream()
                .filter(b -> b.getBillId() == bill.getBillId())
                .findFirst();

        if (updateCandidate.isPresent()) {
            UtilityBill toUpdate = updateCandidate.get();
            toUpdate.payBill();
        }
    }
}
