package com.example.java.customer.api;

import com.example.java.customer.api.dto.CustomerCreateDto;
import com.example.java.customer.api.dto.CustomerDto;
import com.example.java.customer.api.dto.CustomerUpdateDto;
import com.example.java.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Gets customer details", description = "Returns customer details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID id) {
        return ResponseEntity.of(customerService.getCustomer(id));
    }

    @Operation(summary = "Gets list of customers", description = "Returns list of customers with optional filtering")
    @ApiResponse(responseCode = "200", description = "Returned list of customers")
    @GetMapping
    public ResponseEntity<List<CustomerDto>> listCustomers(@RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        return ResponseEntity.ok(customerService.listCustomers());
    }

    @Operation(summary = "Creates a new customer", description = "Creates a new customer returning its details")
    @ApiResponse(responseCode = "201", description = "New customer created")
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerCreateDto);
        return ResponseEntity.created(URI.create("/api/customers/" + createdCustomer.getId())).body(createdCustomer);
    }

    @Operation(summary = "Updates a customer", description = "Updates customer based on its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found and updated"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable UUID id, @RequestBody CustomerUpdateDto customerUpdateDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerUpdateDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @Operation(summary = "Deletes a customer", description = "Deletes a customer with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer found and deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
