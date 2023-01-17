package com.cherkasov.repository;

import com.cherkasov.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class CustomerListRepository {
    private static final List<Customer> CUSTOMERS = new ArrayList<>();

    private static CustomerListRepository instance;
    private static final BiPredicate<Customer, String> CHECK_ID =
            (customer, id) -> customer.getId().equals(id);


    private CustomerListRepository() {
    }

    public static CustomerListRepository getInstance() {
        if (instance == null) {
            instance = new CustomerListRepository();
        }
        return instance;
    }

    public void save(final Customer customer) {
        CUSTOMERS.stream()
                .filter(currentCustomer -> CHECK_ID.test(currentCustomer, customer.getId()))
                .findAny()
                .orElseGet(() -> {
                    CUSTOMERS.add(customer);
                    return customer;
                });
    }

    public Customer[] getAll() {
        return CUSTOMERS.toArray(new Customer[0]);
    }

    public Optional<Customer> getById(final String id) {
        return CUSTOMERS.stream()
                .filter(currentCustomer -> CHECK_ID.test(currentCustomer, id))
                .findAny();
    }


    public void delete(final String id) {
        CUSTOMERS.removeIf(currentCustomer -> CHECK_ID.test(currentCustomer, id));
    }


}
