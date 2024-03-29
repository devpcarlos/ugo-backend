package com.ugo.controller;

import com.myzlab.k.KBuilder;
import static com.ugo.k.generated.metadata.Tables.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
            select(EXPERIENCE.PRICE).
            from(EXPERIENCE).
            multiple().
            buildResponse("experiences");
    }
}
