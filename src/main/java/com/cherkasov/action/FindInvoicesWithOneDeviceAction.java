package com.cherkasov.action;

public class FindInvoicesWithOneDeviceAction implements Action {
    @Override
    public void execute() {
        DATA_ANALYTICS.findInvoicesWithOneDevice();
    }
}
