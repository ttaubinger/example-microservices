package com.example.java.customer.service;

import com.example.java.customer.api.dto.CustomerCreateDto;
import com.example.java.customer.api.dto.CustomerDto;
import com.example.java.customer.api.dto.CustomerUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDto> getCustomer(UUID id);

    List<CustomerDto> listCustomers();

    CustomerDto createCustomer(CustomerCreateDto customerCreateDto);

    CustomerDto updateCustomer(UUID id, CustomerUpdateDto customerUpdateDto);

    void deleteCustomer(UUID id);

}
