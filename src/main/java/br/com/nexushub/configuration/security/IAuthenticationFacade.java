package br.com.nexushub.configuration.security;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface IAuthenticationFacade {

    Authentication getAuthentication();
    UUID getUserAuthenticatedId();
}
