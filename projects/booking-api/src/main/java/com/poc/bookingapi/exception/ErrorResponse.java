package com.poc.bookingapi.exception;

import lombok.Builder;
import org.springframework.http.HttpStatusCode;

@Builder
public class ErrorResponse {
    private String message;
    private HttpStatusCode statusCode;
    private String description;
}
