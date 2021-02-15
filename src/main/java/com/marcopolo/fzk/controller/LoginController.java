package com.marcopolo.fzk.controller;

import com.marcopolo.fzk.domain.JwtResponse;
import com.marcopolo.fzk.domain.LoginRequest;
import com.marcopolo.fzk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody LoginRequest loginRequest){
        JwtResponse jwtResponse = authService.authenticate(loginRequest);
        return jwtResponse;
    }


}
