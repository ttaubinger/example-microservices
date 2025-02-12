package com.example.java.customer.service;

import com.example.java.customer.api.dto.CustomerCreateDto;
import com.example.java.customer.api.dto.CustomerDto;
import com.example.java.customer.api.dto.CustomerUpdateDto;
import com.example.java.customer.api.CustomerApiMapper;
import com.example.java.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private static final CustomerApiMapper customerApiMapper = CustomerApiMapper.INSTANCE;

    public Optional<CustomerDto> getCustomer(UUID id) {
        Optional<CustomerDto> customerDto = customerApiMapper.map(customerRepository.getCustomer(id), Optional.of(CustomerDto.builder().build()));
        if(customerDto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        else return customerDto;
    }

    public List<CustomerDto> listCustomers() {
        return Collections.singletonList(CustomerDto.builder().build());
    }

    public CustomerDto createCustomer(CustomerCreateDto customerCreateDto) {
        CustomerCreateDto newCustomer = CustomerCreateDto.builder()
                .firstName(customerCreateDto.getFirstName())
                .lastName(customerCreateDto.getLastName())
                .email(customerCreateDto.getEmail())
                .build();
        return customerApiMapper.customerToCustomerDto(customerRepository.createCustomer(newCustomer));
    }

    public CustomerDto updateCustomer(UUID id, CustomerUpdateDto customerUpdateDto) {
        getCustomer(id);
        CustomerUpdateDto updatedCustomer = CustomerUpdateDto.builder()
                .firstName(customerUpdateDto.getFirstName())
                .lastName(customerUpdateDto.getLastName())
                .email(customerUpdateDto.getEmail())
                .build();
        return customerApiMapper.customerToCustomerDto(customerRepository.updateCustomer(id, updatedCustomer));
    }

    public void deleteCustomer(UUID id) {
        getCustomer(id);
        customerRepository.deleteCustomer(id);
    }

}
