package com.ecommerceapi.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ecommerceapi.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT para o usuário fornecido.
     * Define o emissor, o email como subject, data de expiração e assina com HMAC256.
     */
    public String genToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // cria e retorna o Token
            return JWT.create()
                    .withIssuer("ecommerce-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExperationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    /**
     * Valida o token JWT recebido e retorna o subject (email do usuário).
     * Se o token for inválido ou expirado, retorna uma string vazia.
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("ecommerce-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
    
    //Gera a data e hora de expiração do token (2 horas à frente da hora atual).
    private Instant genExperationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
