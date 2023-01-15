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
    SORT_CAR_ACTION("Sort all invoices", new SortInvoiceAction()),
    SORT_CAR_ACTION("Sort all invoices", new SortInvoiceAction()),
    SORT_CAR_ACTION("Sort all invoices", new SortInvoiceAction()),
    SORT_CAR_ACTION("Sort all invoices", new SortInvoiceAction()),
    SORT_CAR_ACTION("Sort all invoices", new SortInvoiceAction()),
    SORT_CAR_ACTION("Sort all invoices", new SortInvoiceAction()),
    SORT_CAR_ACTION("Sort all invoices", new SortInvoiceAction()),
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

