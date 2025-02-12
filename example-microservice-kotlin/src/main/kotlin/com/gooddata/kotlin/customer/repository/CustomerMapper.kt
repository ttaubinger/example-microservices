package com.example.kotlin.customer.repository

import com.example.kotlin.customer.api.dto.CustomerCreateDto
import com.example.kotlin.customer.api.dto.CustomerUpdateDto
import com.example.kotlin.customer.service.CustomerModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
interface CustomerMapper {

    companion object {
        val INSTANCE: CustomerMapper = Mappers.getMapper(CustomerMapper::class.java)
    }

    fun customerCreateDtoToEntity(source: CustomerCreateDto): CustomerEntity

    @Mapping(target = "id", ignore = true)
    fun customerUpdateDtoToEntity(source: CustomerUpdateDto): CustomerEntity

    fun customerEntityToModel(source: CustomerEntity): CustomerModel

}
