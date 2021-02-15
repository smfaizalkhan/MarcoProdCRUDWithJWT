package com.marcopolo.fzk.service;

import com.marcopolo.fzk.domain.JwtResponse;
import com.marcopolo.fzk.domain.LoginRequest;
import com.marcopolo.fzk.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        reset(jwtUtil,authenticationManager);
    }

    @Test
    void authenticate() {

        LoginRequest loginRequest = new LoginRequest();
        JwtResponse jwtResponse = new JwtResponse("token");
        when(jwtUtil.generateToken(anyString())).thenReturn(jwtResponse.getToken());
        assertThat(authService.authenticate(loginRequest)).isNotNull();

    }
}