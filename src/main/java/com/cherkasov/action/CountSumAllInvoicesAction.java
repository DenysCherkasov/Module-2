package com.cherkasov.action;

public class CountSumAllInvoicesAction implements Action{
    @Override
    public void execute() {
        DATA_ANALYTICS.countSumAllInvoices();
    }
}
