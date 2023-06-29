package com.jemersoft.pokechallenge.exception.customExceptions;

import org.springframework.web.client.RestClientException;

public class NotFoundException extends RestClientException {
    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
//    public NotFoundException(String message) {
//        super(message);
//    }


}
