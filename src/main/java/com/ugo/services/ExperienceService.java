package com.ugo.services;

import ch.qos.logback.core.read.ListAppender;
import com.myzlab.k.KCollection;
import com.myzlab.k.KException;
import com.ugo.dak.ExperienceDAK;
import com.ugo.payloads.RegisterExperiencePayload;
import com.ugo.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    final ExperienceDAK experienceDAK;
    final JwtTokenProvider jwtTokenProvider;
@Transactional
    public ResponseEntity ResgisterExperience(
            final RegisterExperiencePayload registerExperiencePayload,
            final String token
            ){
try {
    //obtener el ID del usuario asociado al token
    final Claims claims = jwtTokenProvider.decode(token);
    final Long userId = Long.parseLong(claims.get("userId").toString());

   // final Long userId = UUID.fromString(claims.getId()).getMostSignificantBits() & Long.MAX_VALUE;
    registerExperiencePayload.validate();

        // Crear la experiencia asociada al usuario
        experienceDAK.createExperience(registerExperiencePayload, userId);
        return ResponseEntity.ok().build();
}catch(KException e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
    }
 }

 public ResponseEntity ListExperience( final String token){
     //obtener el ID del usuario asociado al token
     final Claims claims = jwtTokenProvider.decode(token);
     final Long userId = Long.parseLong(claims.get("userId").toString());
     KCollection Lista= experienceDAK.fullList(userId);
    return ResponseEntity.ok(Lista.buildResponse());
 }
 public  ResponseEntity fullListAdmin(final String token){
     final Claims claims = jwtTokenProvider.decode(token);
    KCollection Lista = experienceDAK.fullListAdmin();
    return ResponseEntity.ok(Lista.buildResponse());
 }
}

