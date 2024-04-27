package com.ugo.controller;

import com.ugo.payloads.RegisterExperiencePayload;
import com.ugo.services.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    final ExperienceService experienceService;
    public ResponseEntity RegisterExperience(
         @RequestBody final RegisterExperiencePayload registerExperiencePayload,
         @RequestHeader ("Authorizacion") String token
    ){
       return experienceService.ResgisterExperience(registerExperiencePayload, token);
    }

}
