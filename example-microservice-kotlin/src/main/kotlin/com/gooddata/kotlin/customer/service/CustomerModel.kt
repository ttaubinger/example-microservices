package com.example.kotlin.customer.service

import java.util.*


data class CustomerModel(

    private val id: UUID?,
    private val firstName: String?,
    private val lastName: String?,
    private val email: String?

)