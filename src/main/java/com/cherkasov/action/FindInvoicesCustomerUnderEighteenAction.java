package com.cherkasov.action;

public class FindInvoicesCustomerUnderEighteenAction implements Action {
    @Override
    public void execute() {
        DATA_ANALYTICS.findInvoicesCustomerUnderEighteen();
    }
}
