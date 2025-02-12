package com.example.kotlin.customer.repository

import com.example.kotlin.customer.api.dto.CustomerCreateDto
import com.example.kotlin.customer.api.dto.CustomerUpdateDto
import com.example.kotlin.customer.service.CustomerModel
import java.util.*

interface CustomerRepository {

    fun getCustomer(id: UUID): CustomerModel?

    fun createCustomer(newCustomer: CustomerCreateDto): CustomerModel

    fun updateCustomer(id: UUID, updatedCustomer: CustomerUpdateDto): CustomerModel

    fun deleteCustomer(id: UUID)

}
