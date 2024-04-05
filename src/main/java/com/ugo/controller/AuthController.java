package com.ugo.controller;

import com.ugo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity login(
        @RequestHeader(required = false) final String email,
        @RequestHeader(required = false) final String password,
        @RequestHeader(required = false) final String captcha
    ) {
        return authService.login(email, password, captcha);
    }
    
}