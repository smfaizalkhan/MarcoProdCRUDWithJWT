package com.marcopolo.fzk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcopolo.fzk.domain.JwtResponse;
import com.marcopolo.fzk.domain.LoginRequest;
import com.marcopolo.fzk.service.AuthService;
import com.marcopolo.fzk.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = LoginController.class)
@ActiveProfiles(value = "tst")
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;
    @MockBean
    private JwtUtil jwtUtil;


    @Test
    void should_return_200_and_token_whenPassed_userName_and_Pwd() throws Exception {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admin");
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken("testToken");


        when(authService.authenticate(any(LoginRequest.class))).thenReturn(jwtResponse);
        this.mockMvc.perform(post("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is(jwtResponse.getToken())));
    }

    @Test
    void should_return_500_and_BadCreds_whenPassed_wrong_userName_and_Pwd() throws Exception {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admind");


        when(authService.authenticate(any(LoginRequest.class))).thenThrow(new BadCredentialsException("BAD_CREDENTIALS"));
        this.mockMvc.perform(post("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("BAD_CREDENTIALS")));
    }
}