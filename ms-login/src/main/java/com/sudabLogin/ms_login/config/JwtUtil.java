package com.sudabLogin.ms_login.config;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${TOKEN_JWT_SECRET}")
    private String jwtSecret;

    // El token dura 8 horas (una jornada laboral)
    private static final long EXPIRATION_MS = 1000L * 60 * 60 * 8;

    private SecretKey getSigningKey() {
        // La clave debe tener al menos 32 caracteres para HS256
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generarToken(String username, List<String> roles) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(getSigningKey())
                .compact();
    }

    public Claims validarYObtenerClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String obtenerUsername(String token) {
        return validarYObtenerClaims(token).getSubject();
    }

    public boolean esTokenValido(String token) {
        try {
            validarYObtenerClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}