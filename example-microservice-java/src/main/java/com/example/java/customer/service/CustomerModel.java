package com.example.java.customer.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class CustomerModel {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

}
