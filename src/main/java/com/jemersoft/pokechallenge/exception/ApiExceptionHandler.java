package com.jemersoft.pokechallenge.exception;

import com.jemersoft.pokechallenge.exception.customExceptions.BadRequestException;
import com.jemersoft.pokechallenge.exception.customExceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

import java.net.NoRouteToHostException;
import java.net.UnknownHostException;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    private static final String BASE_MSG = " Error Calling: ";
    // 400 - Bad Request Error:
    @ExceptionHandler({
            BadRequestException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(Exception e, HandlerMethod method){
        log.info(e.getMessage() + BASE_MSG + method.getBeanType().getSimpleName() + "." + method.getMethod().getName() + "().");

        return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    // 404 - Not Found Error:
    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(Exception e, HandlerMethod method){
        log.info(e.getMessage() + BASE_MSG + method.getBeanType().getSimpleName() + "." + method.getMethod().getName() + "().");

        return new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    // 503 - Service Unavailable:
    @ExceptionHandler({
            UnknownHostException.class,
            NoRouteToHostException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse handlePokeApiConnectionException(Exception e, HandlerMethod method){
        log.error(e.getMessage() + BASE_MSG + method.getBeanType().getSimpleName() + "." + method.getMethod().getName() + "().");

        return new ErrorResponse("Connection Error", HttpStatus.SERVICE_UNAVAILABLE.value());
    }

}
