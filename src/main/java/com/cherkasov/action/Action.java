package com.cherkasov.action;

import com.cherkasov.service.PersonService;
import com.cherkasov.service.ShopService;

public interface Action {
        ShopService SHOP_SERVICE = ShopService.getInstance();
        PersonService PERSON_SERVICE = PersonService.getInstance();

        void execute();
    }