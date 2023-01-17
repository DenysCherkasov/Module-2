package com.cherkasov.action;

import lombok.Getter;

@Getter
public enum Actions {
    CREATE_INVOICE("Create one invoice", new CreateInvoiceAction()),
    CREATE_FIFTEEN_INVOICE("Create 15 invoices", new CreateFifteenInvoiceAction()),

    CREATE_INVOICE_FROM_FILE("Create  invoice from file", new CreateFileInvoiceAction()),

    SHOW_ALL("Show all invoices", new ShowAllAction()),
    FIND_INVOICE("Find invoice", new FindAction()),
    DELETE_CAR("Delete invoice", new DeleteInvoiceAction()),
    SORT_CAR_ACTION("Sort all invoices", new SortInvoiceAction()),
    COUNT_DEVICES("Count all devices in invoices by type", new CountDevicesAction()),
    MINIMAL_INVOICE_SUM_ACTION("Find the invoice with minimal sum",
            new MinimalInvoiceSumAction()),
    SUM_ALL_INVOICES("Count the sum of all invoices", new CountSumAllInvoicesAction()),
    COUNT_RETAIL_INVOICES("Count retail invoices", new CountRetailInvoicesAction()),
    FIND_INVOICES_WITH_ONE_DEVICE("Find invoices with one device", new FindInvoicesWithOneDeviceAction()),
    FIND_FIRST_THREE_INVOICES("Find first three invoices", new FindFirstThreeInvoicesAction()),
    FIND_INVOICES_CUSTOMER_UNDER_EIGHTEEN("Find invoices customer under 18", new FindInvoicesCustomerUnderEighteenAction()),
    EXIT("Finish program", new ExitAction());

    private final String name;
    private final Action action;

    Actions(final String name, final Action action) {
        this.name = name;
        this.action = action;
    }

    public void execute() {
        action.execute();
    }

}

