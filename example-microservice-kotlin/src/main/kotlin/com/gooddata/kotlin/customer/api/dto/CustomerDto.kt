package com.example.kotlin.customer.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class CustomerDto(

    val id: UUID? = null,
    @Schema(
        description = "Customer's first name",
        name = "firstName",
        type = "String",
        example = "George"
    )
    val firstName: String?,
    @Schema(
        description = "Customer's last name",
        name = "lastName",
        type = "String",
        example = "Clooney"
    )
    val lastName: String?,
    @Schema(
        description = "Customer's email address",
        name = "email",
        type = "String",
        example = "george.clooney@gmail.com"
    )
    val email: String?

)

{

    constructor(): this(null, "", "", "")

}