package com.ugo.controller;

import com.ugo.services.AuthService;
import com.ugo.services.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    private LogoutService logoutService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestHeader(required = false) final String email,
            @RequestHeader(required = false) final String password,
            @RequestHeader(required = false) final String captcha
            ) {
        return authService.login(email, password, captcha);
    }

    @GetMapping("/logout")
    public ResponseEntity logout( @RequestHeader("Authorization") String token ) {
        logoutService.clearUserSessionData(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}