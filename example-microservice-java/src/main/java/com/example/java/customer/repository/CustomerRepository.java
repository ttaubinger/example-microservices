package com.example.java.customer.repository;

import com.example.java.customer.service.CustomerModel;
import com.example.java.customer.api.dto.CustomerCreateDto;
import com.example.java.customer.api.dto.CustomerUpdateDto;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<CustomerModel> getCustomer(UUID id);

    CustomerModel createCustomer(CustomerCreateDto newCustomer);

    CustomerModel updateCustomer(UUID id, CustomerUpdateDto updatedCustomer);

    void deleteCustomer(UUID id);

}
