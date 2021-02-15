package com.marcopolo.fzk.exceptionhandler;

import com.marcopolo.fzk.error.MarcoError;
import com.marcopolo.fzk.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class MarcoExceptionHandlerTest {

    @InjectMocks
    private  MarcoExceptionHandler marcoExceptionHandler;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleBadCredentialsException(){
        BadCredentialsException badCredentialsException = new BadCredentialsException("BADCREDENTIALS");
        ResponseEntity<Object> marcoExceptionResponse= marcoExceptionHandler.handleBadCredentialsException(badCredentialsException);
        assertThat(marcoExceptionResponse.getBody()).isInstanceOf(MarcoError.class);
    }

    @Test
    void handleProductNotFoundException(){
        ProductNotFoundException productNotFoundException = new ProductNotFoundException("BADCREDENTIALS");
        ResponseEntity<Object> marcoExceptionResponse= marcoExceptionHandler.handleProductNotFoundException(productNotFoundException);
        assertThat(marcoExceptionResponse.getBody()).isInstanceOf(MarcoError.class);
    }
}
