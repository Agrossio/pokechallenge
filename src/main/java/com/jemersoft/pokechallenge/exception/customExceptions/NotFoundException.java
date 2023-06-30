package com.jemersoft.pokechallenge.exception.customExceptions;

import org.springframework.web.client.RestClientException;

public class NotFoundException extends RestClientException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
