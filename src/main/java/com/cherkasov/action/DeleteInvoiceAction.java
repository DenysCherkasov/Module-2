package com.cherkasov.action;

import com.cherkasov.util.UserInput;

public class DeleteInvoiceAction implements Action {
    @Override
    public void execute() {
        final String inputId = UserInput.inputId();
        SHOP_SERVICE.delete(inputId);
    }

}
