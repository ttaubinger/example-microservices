package com.example.kotlin.customer.api

import com.example.kotlin.customer.api.dto.CustomerDto
import com.example.kotlin.customer.service.CustomerModel
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import java.util.*

@Mapper
abstract class CustomerApiMapper {

    abstract fun customerToCustomerDto(source: CustomerModel): CustomerDto

    @AfterMapping
    fun map(source: Optional<CustomerModel>, @MappingTarget target: Optional<CustomerDto>): Optional<CustomerDto> {
        return source.map { customerToCustomerDto(it) }
    }

}