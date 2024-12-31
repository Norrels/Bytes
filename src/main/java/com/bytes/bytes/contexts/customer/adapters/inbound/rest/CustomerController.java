package com.bytes.bytes.contexts.customer.adapters.inbound.rest;

import com.bytes.bytes.contexts.customer.adapters.inbound.dtos.CustomerReq;
import com.bytes.bytes.contexts.customer.adapters.outbound.persistence.entity.CustomerEntity;
import com.bytes.bytes.contexts.customer.application.CustomerService;
import com.bytes.bytes.contexts.customer.domain.models.Customer;
import com.bytes.bytes.contexts.customer.mapper.CustomerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer", description = "Endpoints de cliente")
public class CustomerController {
    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Operation(summary = "Cria cliente")
    @PostMapping()
    @Transactional
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerReq customerReq){
       try {
           Customer customer = customerService.create(customerMapper.toCustomer(customerReq));
           return ResponseEntity.ok().body(customer);
       } catch (RuntimeException e){
          return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @Operation(summary = "Busca cliente por CPF")
    @GetMapping("/{cpf}")
    public ResponseEntity<Object> findCustomerByCPF(@PathVariable String cpf){
        try {
            Customer customer = customerService.findByCPF(cpf);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualiza cliente")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerEntity customerReq){
        try {
            if(!Objects.equals(customerReq.getId(), id)) {
                throw new RuntimeException("O id enviado no corpo e na url são diferentes");
            };

            Customer customer = customerService.update(id, customerMapper.toCustomer(customerReq));
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
