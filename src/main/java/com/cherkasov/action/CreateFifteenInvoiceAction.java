package com.cherkasov.action;

public class CreateFifteenInvoiceAction implements Action {
    @Override
    public void execute() {
        SHOP_SERVICE.createFifteenRandomInvoice();
    }

}
