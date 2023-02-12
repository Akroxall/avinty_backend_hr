package com.avinty.hr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.avinty.hr.security.jwt.JWTConfig;
import com.avinty.hr.security.jwt.JwtUtils;

@ExtendWith(MockitoExtension.class)
class JWTUtilsTests {

    private static String USER_NAME = "admin@avinty.hu";

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails principal;

    @Mock
    private JWTConfig jwtConfig;

    @InjectMocks
    private JwtUtils jwtUtils;

    @BeforeEach
    void init() {
        when(jwtConfig.getJwtExpiration()).thenReturn(Duration.ofHours(24));
        when(jwtConfig.getJwtSecret()).thenReturn("secret");
        when(authentication.getPrincipal()).thenReturn(principal);
        when(principal.getUsername()).thenReturn(USER_NAME);  
    }

    @Test
    void generateJWTTokenTest() {
        assertDoesNotThrow(() -> jwtUtils.generateJwtToken(authentication));
    }

    @Test
    void valideJwtTokenTest() {
        String token = jwtUtils.generateJwtToken(authentication);
        assertTrue(jwtUtils.validateJwtToken(token));
    }

    @Test
    void otherExceptionJwtTokenTest() {
        String token = jwtUtils.generateJwtToken(authentication);
        assertFalse(jwtUtils.validateJwtToken(token + "2"));
    }

    @Test
    void expiredJwtTokenTest() {
        when(jwtConfig.getJwtExpiration()).thenReturn(Duration.ofMillis(1));
        String token = jwtUtils.generateJwtToken(authentication);
        assertFalse(jwtUtils.validateJwtToken(token));
    }

    @Test
    void getUserNameFromToken() {
        String token = jwtUtils.generateJwtToken(authentication);
        assertEquals(jwtUtils.getUserNameFromJwtToken(token), USER_NAME);
    }

}
