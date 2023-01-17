package com.cherkasov.repository;

import com.cherkasov.model.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class InvoiceListRepository {
    private static final List<Invoice> INVOICES = new ArrayList<>();

    private static InvoiceListRepository instance;
    private static final BiPredicate<Invoice, String> CHECK_ID =
            (invoice, id) -> invoice.getId().equals(id);


    private InvoiceListRepository() {
    }

    public static InvoiceListRepository getInstance() {
        if (instance == null) {
            instance = new InvoiceListRepository();
        }
        return instance;
    }

    public void save(final Invoice invoice) {
        INVOICES.stream()
                .filter(currentInvoice -> CHECK_ID.test(currentInvoice, invoice.getId()))
                .findAny()
                .orElseGet(() -> {
                    INVOICES.add(invoice);
                    return invoice;
                });
    }

    public Invoice[] getAll() {
        return INVOICES.toArray(new Invoice[0]);
    }

    public Optional<Invoice> getById(final String id) {
        return INVOICES.stream()
                .filter(currentInvoice -> CHECK_ID.test(currentInvoice, id))
                .findAny();
    }


    public void delete(final String id) {
        INVOICES.removeIf(currentInvoice -> CHECK_ID.test(currentInvoice, id));
    }

}
