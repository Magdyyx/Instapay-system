package instapay.Modules.ViewModels;

import instapay.Enums.MoneyProvider;

public class MoneyTransferViewModel {
    private String receiver;
    private MoneyProvider provider;
    private double amount;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public MoneyProvider getProvider() {
        return provider;
    }

    public void setProvider(MoneyProvider provider) {
        this.provider = provider;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
