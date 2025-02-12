package com.example.kotlin.customer.repository

import com.example.kotlin.customer.api.dto.CustomerCreateDto
import com.example.kotlin.customer.api.dto.CustomerUpdateDto
import com.example.kotlin.customer.service.CustomerModel
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomerRepositoryImpl(
    private val customerJpaRepository: CustomerJpaRepository
) : CustomerRepository {

    private val customerMapper = CustomerMapper.INSTANCE

    override fun getCustomer(id: UUID): CustomerModel? {
        return customerJpaRepository.findById(id)
            .map(customerMapper::customerEntityToModel)
            .orElse(null)
    }

    override fun createCustomer(newCustomer: CustomerCreateDto): CustomerModel {
        val createdCustomerEntity = customerMapper.customerCreateDtoToEntity(newCustomer)
        return customerMapper.customerEntityToModel(customerJpaRepository.save(createdCustomerEntity))
    }

    override fun updateCustomer(id: UUID, updatedCustomer: CustomerUpdateDto): CustomerModel {
        val updatedCustomerEntity = customerMapper.customerUpdateDtoToEntity(updatedCustomer)
        return customerMapper.customerEntityToModel(customerJpaRepository.save(updatedCustomerEntity))
    }

    override fun deleteCustomer(id: UUID) {
        customerJpaRepository.deleteById(id)
    }

}
