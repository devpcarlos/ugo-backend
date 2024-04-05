package com.ugo.controller;

import com.myzlab.k.KBuilder;
import com.myzlab.k.KFunction;
import com.myzlab.k.KValues;
import static com.ugo.k.generated.metadata.Tables.*;

import com.ugo.dak.AppUserDAK;
import com.ugo.k.generated.mappers.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JwtController {
    
    private final KBuilder k;

    @PostMapping("/generateToken")
    public String generateToken(){
        //Genera un token JWT con el sujeto "Carlos" y firma con el algoritmo HS256 y una clave "MiPrueba"
        String token = Jwts.builder()
                .setSubject("Carlos")
                .signWith(SignatureAlgorithm.HS256, "MiPrueba")
                .compact();
        //Devuelve el token JWT generado como respuesta del servicio web
        return token;
    }
    
    @GetMapping("/test")
    public ResponseEntity test(){
        return k.
            select(ROLE.NAME).
            from(ROLE).
            multiple().
            buildResponse("experiences");
    }
    
    @GetMapping("/register")
    public ResponseEntity register(){
        final KValues role = KFunction.values()
                .append("El nombre del rol", LocalDateTime.now());
        
            k
            .insertInto(ROLE)
            .columns(
                ROLE.NAME,
                ROLE.CREATED_AT
            )
            .values(role)
            .execute();

        
        return ResponseEntity.ok().build();
    }
}
