package com.cherkasov.analytics;

import com.cherkasov.model.DeviceType;
import com.cherkasov.model.Invoice;
import com.cherkasov.repository.InvoiceListRepository;
import com.cherkasov.service.ShopService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public class DataAnalytics {
    InvoiceListRepository invoiceListRepository;
    private static DataAnalytics instance;

    public DataAnalytics() {
        this.invoiceListRepository = InvoiceListRepository.getInstance();
    }

    public static DataAnalytics getInstance() {
        if (instance == null) {
            instance = new DataAnalytics();
        }
        return instance;
    }


    public int countDevices(DeviceType deviceType) {
        int count = (int) Arrays.stream(invoiceListRepository.getAll())
                .flatMap(invoice -> invoice.getGoods().stream())
                .filter(device -> device.getDeviceType() == deviceType)
                .count();
        System.out.println("Total count of " + deviceType + ": " + count);
        return count;
    }

    public Optional<Double> minimalInvoiceSum() {
        Optional<Double> minimalInvoiceSum = (Arrays.stream(invoiceListRepository.getAll())
                .min(comparator)
                .map(invoice -> {
                    double sum = invoice.getGoods().stream()
                            .flatMapToDouble(device -> DoubleStream.of(device.getPrice()))
                            .sum();
                    System.out.println(invoice.getCustomer() + "Minimal Invoice sum: " + sum);
                    return sum;
                }));
        return minimalInvoiceSum;
    }

    final Comparator<Invoice> comparator = (o1, o2) -> {
        double sum1 = o1.getGoods().stream()
                .flatMapToDouble(device -> DoubleStream.of(device.getPrice()))
                .sum();
        double sum2 = o2.getGoods().stream()
                .flatMapToDouble(device -> DoubleStream.of(device.getPrice()))
                .sum();
        return Double.compare(sum1, sum2);
    };

    public double countSumAllInvoices() {
        double count = Arrays.stream(invoiceListRepository.getAll())
                .flatMapToDouble(invoice -> DoubleStream.of(invoice.getGoods().stream()
                        .flatMapToDouble(device -> DoubleStream.of(device.getPrice()))
                        .sum()))
                .sum();
        System.out.println("Total sum of all invoices: " + count);
        return count;
    }

    public int countRetailInvoices() {
        int count = (int) Arrays.stream(invoiceListRepository.getAll())
                .filter(invoice -> invoice.getType().equals("Retail"))
                .count();
        System.out.println("Total count of retail invoices:" + count);
        return count;
    }

    public List<Invoice> findInvoicesWithOneDevice() {
        return Arrays.stream(invoiceListRepository.getAll())
                .filter(invoice -> invoice.getGoods().size() == 1)
                .peek(invoice -> System.out.println("Invoices with one device: " + invoice))
                .toList();
    }

    public List<Invoice> findFirstThreeInvoices() {
        return Arrays.stream(invoiceListRepository.getAll())
                .limit(3)
                .peek(invoice -> System.out.println("First 3 invoices: " + invoice))
                .toList();
    }

    public List<Invoice> findInvoicesCustomerUnderEighteen() {
        return Arrays.stream(invoiceListRepository.getAll())
                .filter(invoice -> invoice.getCustomer().getAge() < 18)
                .peek(invoice -> {
                    invoice.setType("low_age");
                    System.out.println("Invoices with customer under 18: " + invoice);
                })
                .toList();
    }
}
