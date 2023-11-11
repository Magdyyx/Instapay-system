package instapay.IDAOs;

import java.util.List;

import instapay.Abstractions.UtilityBill;

public interface IUtilityBillDAO {
    UtilityBill getUtilityBillById(int billId);
    List<UtilityBill> getUtilityBillsByUserId(int userId);
    void addUtilityBill(UtilityBill utilityBill);
    void updateUtilityBill(UtilityBill utilityBill);
    void deleteUtilityBill(int billId);
}