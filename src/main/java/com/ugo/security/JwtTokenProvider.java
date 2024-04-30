package com.ugo.security;

import com.myzlab.k.KException;
import com.myzlab.k.helper.KExceptionHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
 public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private  int jwtExpiration;

    // Convierte la clave secreta de una cadena a un objeto SecretKey
    private SecretKey getSecretKey(){
        byte[] decodeKey = Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(decodeKey);
    }
    public String generateToken(final String email,
                                final Long userId,
                                final Long roleId,
                                final String name) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration * 1000L);
        // Agregar reclamaciones privadas
        return Jwts.builder()
                .subject(email)
                .claim("email", email)
                .claim("Id", userId)
                .claim("role_id",roleId )
                .claim("name", name)
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(getSecretKey())
                .compact();
    }
    public Claims decode(String token) throws KException {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw KExceptionHelper.unauthorized("El token JWT ha expirado");
        }
    }
}