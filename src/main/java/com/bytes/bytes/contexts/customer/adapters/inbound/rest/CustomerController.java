package com.bytes.bytes.contexts.customer.adapters.inbound.rest;

import com.bytes.bytes.contexts.customer.application.CustomerService;
import com.bytes.bytes.contexts.customer.domain.models.Customer;
import com.bytes.bytes.contexts.customer.domain.ports.inbound.CustomerServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer", description = "Endpoints de cliente")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Cria usuário")
    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customerReq){
        Customer customer = customerService.create(customerReq);

        return ResponseEntity.ok().body(customer);
    }
}
