package br.com.nexushub.configuration.auth.security;

import br.com.nexushub.configuration.auth.jwt.*;
import br.com.nexushub.usecases.account.model.ApplicationUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder encoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;
    private final JwtTokenHelper jwtTokenHelper;

    public ApplicationSecurityConfig(PasswordEncoder encoder,
                                     ApplicationUserService applicationUserService,
                                     SecretKey secretKey,
                                     JwtProperties jwtProperties, JwtTokenHelper jwtTokenHelper) {
        this.encoder = encoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtProperties = jwtProperties;
        this.jwtTokenHelper = jwtTokenHelper;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtProperties, jwtTokenHelper))
                .addFilterAfter(new JwtTokenVerifier(jwtProperties, jwtTokenHelper), JwtAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/refresh-token").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

}
