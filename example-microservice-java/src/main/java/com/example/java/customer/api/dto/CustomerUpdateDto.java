package com.example.java.customer.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerUpdateDto {

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
