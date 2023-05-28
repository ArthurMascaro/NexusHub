package br.com.nexushub.configuration.properties;

import br.com.nexushub.configuration.properties.model.JwtProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "security.jwt")
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }
}