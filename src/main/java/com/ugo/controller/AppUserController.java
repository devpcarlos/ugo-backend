package com.ugo.controller;

import com.ugo.payloads.RegisterAppUserPayload;
import com.ugo.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/create")
    public ResponseEntity register(
        @RequestBody final RegisterAppUserPayload registerAppUserPayload
    ) {
        return appUserService.register(registerAppUserPayload);
    }
}
