package com.cherkasov.action;

public class CreateInvoiceAction implements Action {
    @Override
    public void execute() {
        SHOP_SERVICE.createRandomInvoice();
    }
}
