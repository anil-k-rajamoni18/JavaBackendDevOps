package com.poc.bookingapi.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookException extends RuntimeException {
    private String description;
    private HttpStatusCode httpStatusCode;
    public BookException(String message) {
        super(message);
    }

    public BookException(final String description, final HttpStatusCode httpStatusCode){
        super(description);
        this.description = description;
        this.httpStatusCode = httpStatusCode;
    }
}
