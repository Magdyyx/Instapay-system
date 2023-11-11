package instapay.DataAccess.Repositories;
import instapay.IDAOs.IUtilityBillDAO;
import instapay.Abstractions.UtilityBill;

import java.util.List;

public class UtilityBillDAO implements IUtilityBillDAO {
    @Override
    public UtilityBill getUtilityBillById(int billId) {
        return null;
    }

    @Override
    public List<UtilityBill> getUtilityBillsByUserId(int userId) {
        return null;
    }

    @Override
    public void addUtilityBill(UtilityBill utilityBill) {

    }

    @Override
    public void updateUtilityBill(UtilityBill utilityBill) {

    }

    @Override
    public void deleteUtilityBill(int billId) {

    }
}
