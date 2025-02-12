package com.example.java.customer.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class CustomerDto {

    private UUID id;
    @Schema(
            description = "Customer's first name",
            name = "firstName",
            type = "String",
            example = "George"
    )
    private String firstName;
    @Schema(
            description = "Customer's last name",
            name = "lastName",
            type = "String",
            example = "Clooney"
    )
    private String lastName;
    @Schema(
            description = "Customer's email address",
            name = "email",
            type = "String",
            example = "george.clooney@gmail.com"
    )
    private String email;

}
