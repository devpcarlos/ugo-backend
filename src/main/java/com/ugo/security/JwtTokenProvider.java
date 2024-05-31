package com.ugo.security;

import com.myzlab.k.KException;
import com.myzlab.k.helper.KExceptionHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
 public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private  int jwtExpiration;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Convierte la clave secreta de una cadena a un objeto SecretKey
    private SecretKey getSecretKey(){
        byte[] decodeKey = Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(decodeKey);
    }
    public String generateToken(final String email,
                                final Long userId,
                                final Long roleId,
                                final String name) {
        //Generar UUID
        String uuid = UUID.randomUUID().toString();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration * 1000L);
        // Agregar reclamaciones privadas
        String token = Jwts.builder()
                .subject(uuid)
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(getSecretKey()).compact();

        redisTemplate.opsForHash().put("Token:" + uuid, "email", email);
        redisTemplate.opsForHash().put("Token:" + uuid, "Id", userId);
        redisTemplate.opsForHash().put("Token:" + uuid, "role_id", roleId);
        redisTemplate.opsForHash().put("Token:" + uuid, "name", name);
        return token;
    }
    public Claims decode(String token) throws KException {
        try {
            String uuid= Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();

            // Consultar Redis para obtener los datos
            String email = (String) redisTemplate.opsForHash().get("Token:" + uuid, "email");
            Long userId = (Long) redisTemplate.opsForHash().get("Token:" + uuid, "Id");
            Long rolId = (Long) redisTemplate.opsForHash().get("Token:" + uuid, "role_id");
            String name = (String) redisTemplate.opsForHash().get("Token:" + uuid, "name");



            Boolean key = redisTemplate.hasKey("Token:" + uuid);
            if (!key){
                throw KExceptionHelper.unauthorized("El token JWT ha sido eliminado. Inicie sesión nuevamente.");
            }

            // Construir y retornar los claims
            ClaimsBuilder claimsBuilder = Jwts.claims();
            claimsBuilder.add("email", email);
            claimsBuilder.add("name", name);
            claimsBuilder.add("role_id", rolId);
            claimsBuilder.add("Id", userId);

            Claims claims = claimsBuilder.build();
            return claims;
        } catch (ExpiredJwtException e) {
            throw KExceptionHelper.unauthorized("El token JWT ha expirado");
        }
    }

    public void deleteToken(String token) {
try {
    String uuid = Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    redisTemplate.delete("Token:" + uuid);

}catch (ExpiredJwtException ej){
    throw KExceptionHelper.unauthorized("El token JWT ha expirado");
}
    }
}