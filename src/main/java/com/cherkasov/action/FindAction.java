package com.cherkasov.action;

import com.cherkasov.util.UserInput;

public class FindAction implements Action {
    @Override
    public void execute() {
        final String inputId = UserInput.inputId();
        SHOP_SERVICE.getById(inputId)
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("This invoice is not found"));
    }
}
