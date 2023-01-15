package com.cherkasov.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Customer {
    private final String id;
    private String email;
    private int age;

    public Customer(final String email, final int age) {
        id = UUID.randomUUID().toString();
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Customer (ID: %s, age: %s, email: %s)%n",
                id, age, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(id, customer.id) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, age);
    }
}
