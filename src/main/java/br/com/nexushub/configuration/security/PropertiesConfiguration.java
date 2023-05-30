package br.com.nexushub.configuration.security;

import br.com.nexushub.configuration.security.jwt.JwtUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "security.jwt")
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
