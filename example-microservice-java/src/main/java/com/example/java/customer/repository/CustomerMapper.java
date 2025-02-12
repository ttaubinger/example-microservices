package com.example.java.customer.repository;

import com.example.java.customer.service.CustomerModel;
import com.example.java.customer.api.dto.CustomerCreateDto;
import com.example.java.customer.api.dto.CustomerUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity customerCreateDtoToEntity(CustomerCreateDto source);

    @Mapping(target = "id", ignore = true)
    CustomerEntity customerUpdateDtoToEntity(CustomerUpdateDto source);

    CustomerModel customerEntityToModel(CustomerEntity source);

}
