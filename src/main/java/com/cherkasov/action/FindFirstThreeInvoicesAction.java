package com.cherkasov.action;

public class FindFirstThreeInvoicesAction implements Action {
    @Override
    public void execute() {
        DATA_ANALYTICS.findFirstThreeInvoices();
    }
}
