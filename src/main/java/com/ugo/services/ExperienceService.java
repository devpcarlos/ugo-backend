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

    try {
        //obtener el ID del usuario asociado al token
        final Claims claims = jwtTokenProvider.decode(token);
        final Long userId = Long.parseLong(claims.get("userId").toString());
        final Long rolId = Long.parseLong(claims.get("role_id").toString());

        KCollection experiences;

        if (rolId.equals("Anfitrion")) {
            // Obtener todas las experiencias
            experiences = experienceDAK.fullList(null);
        } else if (rolId.equals("Turista")) {
            // Filtrar por ID del usuario actual
            experiences = experienceDAK.fullList(userId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Rol de usuario no válido");
        }
        return ResponseEntity.ok(experiences.buildResponse());
    }catch (KException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
    }
 }
}

