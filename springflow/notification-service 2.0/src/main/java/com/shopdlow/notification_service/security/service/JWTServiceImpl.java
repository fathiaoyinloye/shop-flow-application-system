package com.shopdlow.notification_service.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService{
    private final UserDetailsService userDetailsService;

    @Override
    public String generateToken(Authentication authResult) {
        return   JWT.create()
                .withClaim("username", authResult.getPrincipal().toString())
                .withExpiresAt(Instant.now().plusSeconds(60 * 60 * 24))
                .sign(Algorithm.
                        HMAC256(Base64.getEncoder()
                                .encode("our very secret-key".getBytes())));
    }

    @Override
    public UserDetails verify(String jwt) {
       JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Base64.getEncoder()
                .encode("our very secret-key".getBytes())))
                .withClaimPresence("username")
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
        String username = decodedJWT.getClaim("username").asString();
        return userDetailsService.loadUserByUsername(username);
    }
}
