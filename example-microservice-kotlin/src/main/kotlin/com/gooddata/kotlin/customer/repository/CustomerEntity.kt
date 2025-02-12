package com.example.kotlin.customer.repository

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import java.util.*

@Entity
@Table(name = "customers")
data class CustomerEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    val firstName: String,
    val lastName: String,
    @Email
    val email: String

)
