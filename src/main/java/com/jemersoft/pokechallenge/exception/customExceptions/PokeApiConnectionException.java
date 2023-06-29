package com.jemersoft.pokechallenge.exception.customExceptions;

import org.springframework.web.client.RestClientException;

public class PokeApiConnectionException extends RestClientException {
    public PokeApiConnectionException(String msg) {
        super(msg);
    }

    public PokeApiConnectionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
