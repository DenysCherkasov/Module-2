package com.cherkasov.action;

public class CountRetailInvoicesAction implements Action{

    @Override
    public void execute() {
        DATA_ANALYTICS.countRetailInvoices();
    }

}
