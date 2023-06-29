package com.jemersoft.pokechallenge.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    private boolean success = false;
    private final String message;
    private final Integer statusCode;
    private LocalDateTime timestamp = LocalDateTime.now();

}
