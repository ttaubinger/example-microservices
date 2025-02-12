package com.example.kotlin.customer.api

import com.example.kotlin.customer.api.dto.CustomerCreateDto
import com.example.kotlin.customer.api.dto.CustomerDto
import com.example.kotlin.customer.api.dto.CustomerUpdateDto
import com.example.kotlin.customer.service.CustomerServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/v1/customers")
@Validated
class CustomerController(private val customerServiceImpl: CustomerServiceImpl) {

    @Operation(summary = "Gets customer details", description = "Returns customer details by id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Customer found"),
        ApiResponse(responseCode = "404", description = "Customer not found")
    ])
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: UUID): ResponseEntity<CustomerDto> {
        return ResponseEntity.of(customerServiceImpl.getCustomer(id))
    }

    @Operation(summary = "Gets list of customers", description = "Returns list of customers with optional filtering")
    @ApiResponse(responseCode = "200", description = "Returned list of customers")
    @GetMapping
    fun listCustomers(@RequestParam(required = false) name: String?, @RequestParam(required = false) email: String?): ResponseEntity<List<CustomerDto>> {
        return ResponseEntity.ok(customerServiceImpl.listCustomers())
    }

    @Operation(summary = "Creates a new customer", description = "Creates a new customer returning its details")
    @ApiResponse(responseCode = "201", description = "New customer created")
    @PostMapping
    fun createCustomer(@RequestBody customerCreateDto: CustomerCreateDto): ResponseEntity<CustomerDto> {
        val createdCustomer = customerServiceImpl.createCustomer(customerCreateDto)
        return ResponseEntity.created(URI.create("/api/customers/${createdCustomer.id}")).body(createdCustomer)
    }

    @Operation(summary = "Updates a customer", description = "Updates customer based on its id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Customer found and updated"),
        ApiResponse(responseCode = "404", description = "Customer not found")
    ])
    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: UUID, @RequestBody customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerDto> {
        val updatedCustomer = customerServiceImpl.updateCustomer(id, customerUpdateDto)
        return ResponseEntity.ok(updatedCustomer)
    }

    @Operation(summary = "Deletes a customer", description = "Deletes a customer with id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Customer found and deleted"),
        ApiResponse(responseCode = "404", description = "Customer not found")
    ])
    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: UUID): ResponseEntity<Unit> {
        customerServiceImpl.deleteCustomer(id)
        return ResponseEntity.noContent().build()
    }
}