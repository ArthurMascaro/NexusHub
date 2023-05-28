package br.com.nexushub.configuration.security;

import br.com.nexushub.configuration.properties.model.JwtProperties;
import br.com.nexushub.configuration.security.jwt.JwtTokenVerifier;
import br.com.nexushub.configuration.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import br.com.nexushub.usecases.account.ApplicationUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserService applicationUserService;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;
    private final ObjectMapper objectMapper;

    public ApplicationSecurityConfig(ApplicationUserService applicationUserService, PasswordEncoder passwordEncoder, SecretKey secretKey, JwtProperties jwtProperties, ObjectMapper objectMapper) {
        this.applicationUserService = applicationUserService;
        this.passwordEncoder = passwordEncoder;
        this.secretKey = secretKey;
        this.jwtProperties = jwtProperties;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(
                        authenticationManager(), objectMapper, jwtProperties, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtProperties),
                        JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers( "/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                        .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);

        return provider;
    }

}
