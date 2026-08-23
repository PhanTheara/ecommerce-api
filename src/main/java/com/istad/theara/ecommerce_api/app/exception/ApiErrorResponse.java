package com.istad.theara.ecommerce_api.app.exception;
import lombok.Builder;
import java.time.Instant;

@Builder
public record ApiErrorResponse<T>(
        Integer code,
        Boolean IsSuccess,
        String message,
        Instant timestamp,
        T errorDetail
) { }
