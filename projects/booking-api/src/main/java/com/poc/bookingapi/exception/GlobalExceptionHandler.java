package com.poc.bookingapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<Map<String, String>> handleBookException(BookException ex) {
        final LinkedHashMap<String, String> errorResponse = new LinkedHashMap<>();
        errorResponse.put("error message", ex.getMessage());
        errorResponse.put("status code", ex.getHttpStatusCode().toString());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatusCode());
    }
}
