package com.marcopolo.fzk.service;

import com.marcopolo.fzk.domain.JwtResponse;
import com.marcopolo.fzk.domain.LoginRequest;
import com.marcopolo.fzk.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

    public JwtResponse authenticate(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch (AuthenticationException e) {
           throw new BadCredentialsException("BAD_CREDENTIALS");
        }

        String token = jwtUtil.generateToken(loginRequest.getUsername());
        return new JwtResponse(token);
    }
    }

