package com.ugo.services;

import com.ugo.dak.AppUserDAK;
import com.ugo.k.generated.mappers.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppUserService {
    
    private final AppUserDAK appUserDAK;
    
    @Transactional
    public ResponseEntity register(
        final AppUser appUser
    ) {
        appUserDAK.create(appUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}