package instapay.Endpoints;

import instapay.Abstractions.BillingEndpoint;
import instapay.DataAccess.Models.Bill;

public class GasBillingEndpoint extends BillingEndpoint {

    @Override
    public Bill GetBill(String billId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean PayBill(String billId) {
        // TODO Auto-generated method stub
        return false;
    }

}
