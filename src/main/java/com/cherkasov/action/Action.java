package com.cherkasov.action;

import com.cherkasov.analytics.DataAnalytics;
import com.cherkasov.service.ShopService;

public interface Action {
    ShopService SHOP_SERVICE = ShopService.getInstance();
    DataAnalytics DATA_ANALYTICS = DataAnalytics.getInstance();


    void execute();
}