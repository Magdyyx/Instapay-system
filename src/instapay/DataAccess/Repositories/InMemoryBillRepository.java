package instapay.DataAccess.Repositories;

import instapay.Abstractions.BillRepository;
import instapay.Abstractions.UtilityBill;
import instapay.DataAccess.Models.ElectricityBill;
import instapay.DataAccess.Models.GasBill;
import instapay.DataAccess.Models.WaterBill;

import java.util.List;
import java.util.Optional;

public class InMemoryBillRepository implements BillRepository {

    private static List<UtilityBill> bills = List.of(
            new ElectricityBill(1, 0, false, 490),
            new GasBill(2, 0, false, 120),
            new WaterBill(3, 0, false, 6894)
    );

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
