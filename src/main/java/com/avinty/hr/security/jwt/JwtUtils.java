package com.avinty.hr.security.jwt;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtUtils {

    @Autowired
    private JWTConfig config;

    public String generateJwtToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date()).setExpiration(Date.from(Instant.now().plus(config.getJwtExpiration())))
                .signWith(SignatureAlgorithm.HS512, config.getJwtSecret()).compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(config.getJwtSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(config.getJwtSecret()).parseClaimsJws(authToken);
            return true;
        }
        catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        }
        catch (Exception e) {
            log.error("Failed to validate : {}", e.getMessage());
        }

        return false;
    }
}
