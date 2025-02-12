package com.example.kotlin.customer.service

import com.example.kotlin.customer.api.CustomerApiMapper
import com.example.kotlin.customer.api.dto.CustomerCreateDto
import com.example.kotlin.customer.api.dto.CustomerDto
import com.example.kotlin.customer.api.dto.CustomerUpdateDto
import com.example.kotlin.customer.repository.CustomerRepository
import jakarta.transaction.Transactional
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
@Transactional(rollbackOn = [Exception::class])
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
) : CustomerService {

    private val customerApiMapper: CustomerApiMapper = Mappers.getMapper(CustomerApiMapper::class.java)

    override fun getCustomer(id: UUID): Optional<CustomerDto> {
        val customerDto = customerApiMapper.map(
            Optional.ofNullable(customerRepository.getCustomer(id)),
            Optional.of(CustomerDto())
        )
        if (customerDto.isEmpty)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")
        return customerDto
    }

    override fun listCustomers(): List<CustomerDto> {
        return listOf(CustomerDto())
    }

    override fun createCustomer(customerCreateDto: CustomerCreateDto): CustomerDto {
        val newCustomer = CustomerCreateDto(
            firstName = customerCreateDto.firstName,
            lastName = customerCreateDto.lastName,
            email = customerCreateDto.email
        )
        return customerApiMapper.customerToCustomerDto(customerRepository.createCustomer(newCustomer))
    }

    override fun updateCustomer(id: UUID, customerUpdateDto: CustomerUpdateDto): CustomerDto {
        getCustomer(id)
        val updatedCustomer = CustomerUpdateDto(
            firstName = customerUpdateDto.firstName,
            lastName = customerUpdateDto.lastName,
            email = customerUpdateDto.email
        )
        return customerApiMapper.customerToCustomerDto(customerRepository.updateCustomer(id, updatedCustomer))
    }

    override fun deleteCustomer(id: UUID) {
        getCustomer(id)
        customerRepository.deleteCustomer(id)
    }
}