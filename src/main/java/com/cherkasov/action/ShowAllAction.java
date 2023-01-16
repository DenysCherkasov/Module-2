package com.cherkasov.action;

import com.cherkasov.model.Invoice;

import java.util.Arrays;

public class ShowAllAction implements Action {
    @Override
    public void execute() {
        Invoice[] all = SHOP_SERVICE.getAll();
        Arrays.stream(all).forEach(System.out::println);
    }
}
