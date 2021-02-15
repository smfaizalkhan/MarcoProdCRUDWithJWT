package com.marcopolo.fzk.exceptionhandler;

import com.marcopolo.fzk.error.MarcoError;
import com.marcopolo.fzk.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MarcoExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex){
       MarcoError error = new MarcoError(ex.getMessage(),"100.100.001");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex){
        MarcoError error = new MarcoError(ex.getMessage(),"100.100.003");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
