package com.busecnky.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.busecnky.repository.enums.ERole;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Optional;

public class JwtTokenManager {
    @Value("${jwt.secretKey}")
    String secretKey;
    @Value("${AUDIENCE}")
    String audience;
    @Value("${jwt.issuer}")
    String issuer;

    public Optional<String> createToken(Long id){
        String token = null;
        Date date = new Date(System.currentTimeMillis()+(1000*5*60));

        try{
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withClaim("id",id)
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC512(secretKey));

            return Optional.of(token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<String> createToken(Long id, ERole role){
        String token = null;
        Date date = new Date(System.currentTimeMillis()+(1000*5*60));

        try{
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withClaim("id",id)
                    .withClaim("role",role.toString())
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC512(secretKey));

            return Optional.of(token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                return false;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }


    public Optional<Long> getIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                return Optional.empty();
            }
            return Optional.of(decodedJWT.getClaim("id").asLong());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Optional.empty();
        }
    }

    public Optional<String> getRoleFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                return Optional.empty();
            }
            return Optional.of(decodedJWT.getClaim("role").asString());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Optional.empty();
        }
    }


}
