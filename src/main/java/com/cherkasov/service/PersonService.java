package com.cherkasov.service;

import com.cherkasov.model.Customer;
import com.cherkasov.repository.CustomerListRepository;
import com.cherkasov.util.Util;

import java.util.Optional;
import java.util.Random;

public class PersonService {
    private final CustomerListRepository customerListRepository;
    private final Random random = new Random();

    private static PersonService instance;

    private PersonService(final CustomerListRepository customerListRepository) {
        this.customerListRepository = customerListRepository;
    }

    public static PersonService getInstance() {
        if (instance == null) {
            instance = new PersonService(CustomerListRepository.getInstance());
        }
        return instance;
    }

    public static PersonService getInstance(final CustomerListRepository repository) {
        if (instance == null) {
            instance = new PersonService(repository);
        }
        return instance;
    }

    public Customer createRundomCustomer() {
        Customer customer = new Customer(Util.getRandomString(), random.nextInt(100));
        customerListRepository.save(customer);
        return customer;
    }

    public Customer[] getAll() {
        return customerListRepository.getAll();
    }

    public Optional<Customer> getById(final String id) {
        return customerListRepository.getById(id);
    }

    public void delete(final String id) {
        customerListRepository.delete(id);
    }


}
