package com.example.kotlin.customer.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerJpaRepository : JpaRepository<CustomerEntity, UUID>