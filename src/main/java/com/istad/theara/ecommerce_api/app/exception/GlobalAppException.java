package com.istad.theara.ecommerce_api.app.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class GlobalAppException {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleException(DataIntegrityViolationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiErrorResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .IsSuccess(false)
                        .message("something went wrong")
                        .timestamp(Instant.now())
                        .errorDetail(exception.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(
                ApiErrorResponse.builder()
                        .code(exception.getStatusCode().value())
                        .IsSuccess(false)
                        .message("Data format or syntax is not correct")
                        .timestamp(Instant.now())
                        .errorDetail(exception.getReason())
                        .build()

        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiErrorResponse<?> handleJsonException(HttpMessageNotReadableException e) {
        return ApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .IsSuccess(false)
                .message("Data format or syntax is not correct")
                .timestamp(Instant.now())
                .errorDetail(e.getLocalizedMessage())
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse<?> handleValidationException(MethodArgumentNotValidException ex) {
    List<Map<String, Object>> errorslist = new ArrayList<>();
        ex.getFieldErrors().forEach(fieldError->{
            Map<String, Object> error = new HashMap<>();
            error.put("field",fieldError.getField());
            error.put("message", fieldError.getDefaultMessage());
             errorslist.add(error);
        });

        return ApiErrorResponse.builder()
                .errorDetail(errorslist)
                .message("Validation Failed")
                .IsSuccess(false)
                .timestamp(Instant.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();

    }
}
