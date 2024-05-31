package com.ugo.services;

import com.myzlab.k.KException;
import com.ugo.constantes.Constantes;
import com.ugo.dak.ExperienceDAK;
import com.ugo.payloads.RegisterExperiencePayload;
import com.ugo.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    final ExperienceDAK experienceDAK;
    final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public ResponseEntity ResgisterExperience(
            final RegisterExperiencePayload registerExperiencePayload,
            final String token
    ) {
            final Claims claims = jwtTokenProvider.decode(token);
            final Long userId = Long.parseLong(claims.get("Id").toString());
            registerExperiencePayload.validate();
            experienceDAK.createExperience(registerExperiencePayload, userId);
            return ResponseEntity.ok().build();
           }
    public ResponseEntity ListExperience( final String token){

        final Claims claims = jwtTokenProvider.decode(token);
        final Long userId = Long.parseLong(claims.get("Id").toString());
        final Long roleId = Long.parseLong(claims.get("role_id").toString());

        return experienceDAK.fullList(roleId.equals(Constantes.ROLE_ADMIN) ? null : userId).buildResponse();
    }
}
