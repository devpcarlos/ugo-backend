package com.ugo.controller;

import com.ugo.payloads.RegisterExperiencePayload;
import com.ugo.services.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    final ExperienceService experienceService;
    @PostMapping("/create")
    public ResponseEntity RegisterExperience(
         @RequestBody final RegisterExperiencePayload registerExperiencePayload,
         @RequestHeader ("Authorizacion") String token
    ){
       return experienceService.ResgisterExperience(registerExperiencePayload, token);
    }
    @GetMapping
    public ResponseEntity ListExperience(
            @RequestHeader ("Authorizacion") String token
    ){
        return experienceService.ListExperience(token);
    }
    @GetMapping("/admin")
    public ResponseEntity ListAdmin(
            @RequestHeader ("Authorizacion") String token
    ){
        return experienceService.fullListAdmin(token);
    }
}
