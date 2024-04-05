package com.ugo.controller;

import com.ugo.k.generated.mappers.AppUser;
import com.ugo.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity register(
        @RequestBody final AppUser appUser
    ) {
        return appUserService.register(appUser);
    }
}
