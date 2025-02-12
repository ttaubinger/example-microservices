package com.example.kotlin.customer.service

import com.example.kotlin.customer.api.dto.CustomerCreateDto
import com.example.kotlin.customer.api.dto.CustomerDto
import com.example.kotlin.customer.api.dto.CustomerUpdateDto
import java.util.*

interface CustomerService {

    fun getCustomer(id: UUID): Optional<CustomerDto>

    fun listCustomers(): List<CustomerDto>

    fun createCustomer(customerCreateDto: CustomerCreateDto): CustomerDto

    fun updateCustomer(id: UUID, customerUpdateDto: CustomerUpdateDto): CustomerDto

    fun deleteCustomer(id: UUID)

}
