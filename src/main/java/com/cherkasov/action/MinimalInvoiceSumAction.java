package com.cherkasov.action;

public class MinimalInvoiceSumAction implements Action {
    @Override
    public void execute() {
        DATA_ANALYTICS.minimalInvoiceSum();
    }
}
