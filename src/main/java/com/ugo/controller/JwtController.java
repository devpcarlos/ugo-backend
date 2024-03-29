package com.ugo.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

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
}
