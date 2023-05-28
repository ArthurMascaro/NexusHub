package br.com.nexushub.configuration.security;

import br.com.nexushub.configuration.security.IAuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UUID getUserAuthenticatedId() {
        return (UUID) getAuthentication().getPrincipal();
    }

}
