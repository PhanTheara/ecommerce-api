package com.istad.theara.ecommerce_api.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "user name required")
        String userName,
        @NotBlank(message = "password required")
        String password,
        @NotBlank(message = "email required")
        String email,
        @NotBlank(message = "first name required")
        String fistName,
        @NotBlank(message = "last name required")
        String lastName,
        @NotBlank(message = "conform  password required")
        String conformPassword
        ) { }
