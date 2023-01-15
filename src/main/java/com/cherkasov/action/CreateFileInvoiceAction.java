package com.cherkasov.action;

public class CreateFileInvoiceAction implements Action {

    @Override
    public void execute() {
        SHOP_SERVICE.createInvoiceFromFile("file.csv");
    }

}
