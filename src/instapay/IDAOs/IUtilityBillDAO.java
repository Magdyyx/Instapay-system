package instapay.IDAOs;

import instapay.Model.UtilityBill;

import java.util.List;

public interface IUtilityBillDAO {
    UtilityBill getUtilityBillById(int billId);
    List<UtilityBill> getUtilityBillsByUserId(int userId);
    void addUtilityBill(UtilityBill utilityBill);
    void updateUtilityBill(UtilityBill utilityBill);
    void deleteUtilityBill(int billId);
}