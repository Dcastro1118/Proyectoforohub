package com.proyecto.forohub.servicios;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.proyecto.forohub.modelos.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Locale;

@Service
public class ServicioToken {


    public String generateToken(Usuario user) {
        Algorithm algorithm = Algorithm.HMAC256("root");

        return JWT.create()
                .withIssuer("foro hub")
                .withSubject(user.getUsername().toLowerCase(Locale.ROOT))
                .withClaim("id",user.getId())
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);
    }

    // genera un tiempo de expiracion apartir de la fechaCreacion actual
    private Instant generateExpirationDate()
    {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-05:00"));
    }

    // Obtener el usuario
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;

        // Validar la firma del token
        try {
            Algorithm algorithm = Algorithm.HMAC256("root");
            verifier = JWT.require(algorithm)
                    .withIssuer("foro hub")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token no valido");
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("No se pudo validar el token");
        }
        return verifier.getSubject();
    }
}
