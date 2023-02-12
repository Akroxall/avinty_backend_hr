package com.avinty.hr.security.jwt;

import java.time.Duration;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("jwt-config")
public class JWTConfig {
    @NotNull
    @NotEmpty
    private String jwtSecret;
    @NotNull
    private Duration jwtExpiration;
}
