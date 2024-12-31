package com.bytes.bytes.contexts.customer.application.useCases;

import com.bytes.bytes.contexts.customer.domain.models.Customer;
import com.bytes.bytes.contexts.customer.domain.ports.outbound.CustomerRepositoryPort;
public class CreateCustomerUseCase {
    private final CustomerRepositoryPort customerRepository;

    public CreateCustomerUseCase(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer) {
        customerRepository.findByCPF(customer.getCpf()).ifPresent((u) -> {
            throw new RuntimeException("Cliente já cadastrado");
        });
        return customerRepository.save(customer);
    }
}
