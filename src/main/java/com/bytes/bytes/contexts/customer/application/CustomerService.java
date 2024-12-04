package com.bytes.bytes.contexts.customer.application;

import com.bytes.bytes.contexts.customer.domain.models.Customer;
import com.bytes.bytes.contexts.customer.domain.ports.inbound.CustomerServicePort;
import com.bytes.bytes.contexts.customer.domain.ports.outbound.CustomerRepositoryPort;

public class CustomerService implements CustomerServicePort {
    private final CustomerRepositoryPort customerRepository;

    public CustomerService(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteBy(Long id) {
        customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        customerRepository.delete(id);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        customer.update(customer.getCpf(), customer.getEmail(), customer.getName(), customer.getTelefone());
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByCPF(String cpf) {
       return customerRepository.findByCPF(cpf).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
