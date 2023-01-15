package com.cherkasov.action;

public class SortInvoiceAction implements Action {
    @Override
    public void execute() {
        SHOP_SERVICE.sortInvoices().forEach(invoice ->
                System.out.println(invoice));
    }
}
