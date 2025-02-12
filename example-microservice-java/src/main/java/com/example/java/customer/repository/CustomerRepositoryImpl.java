package com.example.java.customer.repository;

import com.example.java.customer.api.dto.CustomerCreateDto;
import com.example.java.customer.api.dto.CustomerUpdateDto;
import com.example.java.customer.service.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private CustomerJpaRepository customerJpaRepository;

    private static final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public Optional<CustomerModel> getCustomer(UUID id) {
        return customerJpaRepository.findById(id)
                .map(customerMapper::customerEntityToModel);
    }

    @Override
    public CustomerModel createCustomer(CustomerCreateDto newCustomer) {
        CustomerEntity createdCustomerEntity = customerMapper.customerCreateDtoToEntity(newCustomer);
        return customerMapper.customerEntityToModel(customerJpaRepository.save(createdCustomerEntity));
    }

    @Override
    public CustomerModel updateCustomer(UUID id, CustomerUpdateDto updatedCustomer) {
        CustomerEntity updatedCustomerEntity = customerMapper.customerUpdateDtoToEntity(updatedCustomer);
        return customerMapper.customerEntityToModel(customerJpaRepository.save(updatedCustomerEntity));
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerJpaRepository.deleteById(id);
    }

}
