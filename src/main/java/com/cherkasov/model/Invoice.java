package com.cherkasov.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class Invoice {
    private final Random random = new Random();
    private final int limit = random.nextInt(100000);
    private final String id;
    private Customer customer;
    private String type;
    private List<Device> goods;

    public Invoice(final Customer customer, final List<Device> goods) {
        this.customer = customer;
        this.goods = goods;
        id = UUID.randomUUID().toString();
        type = createType();
    }

    private String createType() {
        double totalPrice = 0;
        for (Device good : goods) {
            totalPrice = totalPrice + good.getPrice();
        }
        if (totalPrice > limit) {
            return "Wholesale";
        } else {
            return "Retail";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id.equals(invoice.id) && Objects.equals(customer, invoice.customer) && Objects.equals(type, invoice.type) && Objects.equals(goods, invoice.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, type, goods);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        goods.forEach(device -> sb.append(device.toString()).append("%n"));
        return String.format("Invoice (Type: %s, Customer: %s)%n, %s",
                type, customer, sb);
    }
}
