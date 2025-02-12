package com.example.java.customer.api;

import com.example.java.customer.api.dto.CustomerDto;
import com.example.java.customer.service.CustomerModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface CustomerApiMapper {

    CustomerApiMapper INSTANCE = Mappers.getMapper(CustomerApiMapper.class);

    CustomerDto customerToCustomerDto(CustomerModel source);

    @AfterMapping
    default Optional<CustomerDto> map(Optional<CustomerModel> source, @MappingTarget Optional<CustomerDto> target) {
        return source.map(this::customerToCustomerDto);
    }

}
