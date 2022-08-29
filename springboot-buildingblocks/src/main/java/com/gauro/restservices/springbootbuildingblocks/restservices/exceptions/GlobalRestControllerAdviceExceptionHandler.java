package com.gauro.restservices.springbootbuildingblocks.restservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * @author Chandra
 */

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails usernameNotFound(UserNameNotFoundException ex){
        return CustomErrorDetails.builder()
                .timestamp(new Date())
                .errorDetails("From @RestController Not Found")
                .message(ex.getMessage())
                .build();
    }
}
