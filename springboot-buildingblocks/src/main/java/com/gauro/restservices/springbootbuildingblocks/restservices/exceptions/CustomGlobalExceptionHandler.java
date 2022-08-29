package com.gauro.restservices.springbootbuildingblocks.restservices.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * @author Chandra
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails=CustomErrorDetails.builder()
                .timestamp(new Date())
                .errorDetails("From MethodArgumentNotValid Exception in GEH")
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails= CustomErrorDetails.builder()
                .timestamp(new Date())
                .errorDetails("From HttpRequestMethodNotSupportedException")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
      //  return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    // UserNameNotFoundException
    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request){
        CustomErrorDetails customErrorDetails=CustomErrorDetails.builder()
                .errorDetails(request.getDescription(false))
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }

    // ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex , WebRequest request){
        CustomErrorDetails customErrorDetails= CustomErrorDetails.builder()
                .errorDetails(request.getDescription(false))
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }





}
