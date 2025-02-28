package com.poc.bookingapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Root controller", description = "API for Health Check ")
public class RootController {

    @GetMapping("/")
    @Operation(
            summary = "Return health check of the service",
            responses = {@ApiResponse(description = "health check", responseCode = "200")}
    )
    public String healthCheck() {
        return "booking api is up and running";
    }
}
