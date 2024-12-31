package com.bytes.bytes.contexts.customer.application.useCases;

import com.bytes.bytes.contexts.customer.domain.CustomerNotFound;
import com.bytes.bytes.contexts.customer.domain.models.Customer;
import com.bytes.bytes.contexts.customer.domain.ports.outbound.CustomerRepositoryPort;

public class UpdateCustomerUseCase {
    private final CustomerRepositoryPort customerRepository;

    public UpdateCustomerUseCase(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Long id, Customer customerToUpdate) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFound::new);

        if (!customer.getEmail().equals(customerToUpdate.getEmail()) && customerRepository.existsByEmail(customerToUpdate.getEmail())) {
            throw new RuntimeException("Este email já está em uso");
        }

        if (!customer.getCpf().equals(customerToUpdate.getCpf()) && customerRepository.existsByCpf(customerToUpdate.getCpf())) {
            throw new RuntimeException("Este CPF já está cadastrado");
        }

        customer.update(customerToUpdate.getCpf(), customerToUpdate.getEmail(), customerToUpdate.getName(), customerToUpdate.getPhone());
        return customerRepository.save(customer);
    }
}
