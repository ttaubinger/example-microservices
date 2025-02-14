package com.example.java.customer.api;

import com.example.java.customer.api.dto.CustomerCreateDto;
import com.example.java.customer.api.dto.CustomerDto;
import com.example.java.customer.api.dto.CustomerUpdateDto;
import com.example.java.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CustomerController.class })
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    static final String CUSTOMERS_URL_PREFIX = "/api/v1/";

    @Autowired
    private CustomerController customerController;

    @MockitoBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @BeforeEach
    void initialize() {
        mvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }

    @Test
    void getCustomer_successfulIfCustomerWithIdExists_Returns200() throws Exception {
        var customerId = UUID.randomUUID();
        var customerDto = CustomerDto.builder()
                .id(customerId)
                .firstName("George")
                .lastName("Clooney")
                .email("george.clooney@gmail.com")
                .build();

        when(customerService.getCustomer(customerId)).thenReturn(Optional.of(customerDto));

        mvc.perform(MockMvcRequestBuilders.get(CUSTOMERS_URL_PREFIX + "customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createCustomer_successfulIfCustomerCreated_Returns201() throws Exception {
        var customerCreateDto = CustomerCreateDto.builder().build();
        var customerDto = CustomerDto.builder().build();

        when(customerService.createCustomer(customerCreateDto)).thenReturn(customerDto);

        mvc.perform(MockMvcRequestBuilders.post(CUSTOMERS_URL_PREFIX + "customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerCreateDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomer_successfulIfCustomerUpdated_Returns200() throws Exception {
        UUID customerId = UUID.randomUUID();
        var customerUpdateDto = CustomerUpdateDto.builder().build();
        var customerDto = CustomerDto.builder().build();

        when(customerService.updateCustomer(customerId, customerUpdateDto)).thenReturn(customerDto);

        mvc.perform(MockMvcRequestBuilders.put(CUSTOMERS_URL_PREFIX + "customers/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerUpdateDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomer_successfulIfCustomerDeleted_Returns204() throws Exception {
        UUID customerId = UUID.randomUUID();

        doNothing().when(customerService).deleteCustomer(customerId);

        mvc.perform(MockMvcRequestBuilders.delete(CUSTOMERS_URL_PREFIX + "customers/{id}", customerId))
                .andExpect(status().isNoContent());
    }

    private String asJsonString(final Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

}
