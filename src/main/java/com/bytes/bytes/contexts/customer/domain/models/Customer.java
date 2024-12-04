package com.bytes.bytes.contexts.customer.domain.models;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.product.ProductInvalidDataException;

public class Customer {
    private Long id;
    private String cpf;
    private String email;
    private String name;
    private String telefone;

    public Long getId() {
        return id;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Customer(Long id, String cpf, String email, String name, String telefone) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.name = name;
        this.telefone = telefone;
        this.validate();
    }

    public void update(String cpf, String email, String name, String telefone) {
        this.cpf = cpf;
        this.email = email;
        this.name = name;
        this.telefone = telefone;
        this.validate();
    }

    public void validate(){
        if(this.cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório");
        }

        if(this.email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("O email não pode ser nulo");
        }

        if(this.name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("O nome do cliente não pode ser nulo");
        }
    }
}
