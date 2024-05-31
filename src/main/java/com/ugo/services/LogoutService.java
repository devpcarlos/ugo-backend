package com.ugo.services;

import com.ugo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public void clearUserSessionData(String token) {

        jwtTokenProvider.deleteToken(token);
    }
}
