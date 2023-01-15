package com.cherkasov;

import com.cherkasov.analytics.DataAnalytics;
import com.cherkasov.model.DeviceType;

import com.cherkasov.model.Invoice;
import com.cherkasov.repository.CustomerListRepository;
import com.cherkasov.repository.InvoiceListRepository;
import com.cherkasov.service.PersonService;
import com.cherkasov.service.ShopService;
import com.cherkasov.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerListRepository customerListRepository = CustomerListRepository.getInstance();
        InvoiceListRepository invoiceListRepository = InvoiceListRepository.getInstance();
        ShopService shopService = ShopService.getInstance(invoiceListRepository);

        shopService.createInvoiceFromFile("file.csv");

        shopService.createFifteenRandomInvoice();
        Invoice [] allInvoices = shopService.getAll();
        for (int i = 0; i < allInvoices.length; i++) {
            System.out.printf( i + "%n " + allInvoices[i] + "%n");
        }
        DataAnalytics dataAnalytics = new DataAnalytics();
        dataAnalytics.countDevices(DeviceType.TELEPHONE);
        dataAnalytics.minimalInvoiceSum();
        dataAnalytics.countSumAllInvoices();
        dataAnalytics.countRetailInvoices();
        dataAnalytics.findInvoicesWithOneDevice();
        System.out.println("-----------------------------------------------------");
        dataAnalytics.findFirstThreeInvoices();
        System.out.println("-----------------------------------------------------");
        dataAnalytics.findInvoicesCustomerUnderEighteen();
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");


        List<Invoice> sortedInvoices = shopService.sortInvoices();
        sortedInvoices.forEach(invoice -> System.out.println(invoice));
    }
}
