package br.com.nexushub.configuration.properties.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

public class JwtProperties {

    private String secretKey;
    private String tokenPrefix;
    private Long tokenExpiration;

    public JwtProperties() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Long getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Long tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    @Bean
    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
