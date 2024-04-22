package com.ugo.services;

import com.ugo.dak.ExperienceDAK;
import com.ugo.payloads.RegisterExperiencePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    final ExperienceDAK experienceDAK;
@Transactional
    public ResponseEntity ResgisterExperience(
            final RegisterExperiencePayload registerExperiencePayload
            ){
        registerExperiencePayload.validate();

        experienceDAK.createExperience(registerExperiencePayload);

        return ResponseEntity.ok().build();

    }

}
