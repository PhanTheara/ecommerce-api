package com.istad.theara.ecommerce_api.features.auth;

import com.istad.theara.ecommerce_api.features.auth.dto.RegisterRequest;

public interface AuthService {
    void registerAuth(RegisterRequest registerRequest);
}
