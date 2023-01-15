package com.cherkasov.util;

import com.cherkasov.model.Invoice;
import com.cherkasov.repository.CustomerListRepository;
import com.cherkasov.repository.InvoiceListRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

public class Util {

    InvoiceListRepository invoiceListRepository = InvoiceListRepository.getInstance();

    public static String getRandomString() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        int length = random.nextInt(5, 10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    final Comparator<Invoice> ageCustomerComparator = (o1, o2) ->
            Integer.compare(o2.getCustomer().getAge(), o1.getCustomer().getAge());

    final Comparator<Invoice> countDevicesComparator = Comparator.comparingInt(o -> o.getGoods().size());

    final Comparator<Invoice> totalPriceComparator = (o1, o2) -> {
        double sum1 = o1.getGoods().stream()
                .flatMapToDouble(device -> DoubleStream.of(device.getPrice()))
                .sum();
        double sum2 = o2.getGoods().stream()
                .flatMapToDouble(device -> DoubleStream.of(device.getPrice()))
                .sum();
        return Double.compare(sum1, sum2);
    };

    final Comparator<Invoice> invoiceComparator = ageCustomerComparator
            .thenComparing(countDevicesComparator)
            .thenComparing(totalPriceComparator);

    public List<Invoice> sortInvoices() {
        return Arrays.stream(invoiceListRepository.getAll())
                .sorted(invoiceComparator)
                .toList();
    }
}
