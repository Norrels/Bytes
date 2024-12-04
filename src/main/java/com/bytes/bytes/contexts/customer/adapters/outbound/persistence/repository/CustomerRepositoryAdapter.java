package com.bytes.bytes.contexts.customer.adapters.outbound.persistence.repository;

import com.bytes.bytes.contexts.customer.domain.models.Customer;
import com.bytes.bytes.contexts.customer.domain.ports.outbound.CustomerRepositoryPort;
import com.bytes.bytes.contexts.customer.mapper.CustomerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;

    @Override
    public Customer save(Customer c) {
        return mapper.toCustomer(repository.save(mapper.toCustomerEntity(c)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Customer> findByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id).map(mapper::toCustomer);
    }
}
