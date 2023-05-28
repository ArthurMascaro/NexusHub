package br.com.nexushub.usecases.account;

import br.com.nexushub.usecases.account.gateway.ApplicationUserDAO;
import br.com.nexushub.usecases.account.model.ApplicationUser;
import br.com.nexushub.web.model.account.request.ApplicationUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplicationUserService implements ApplicationUserCRUD, UserDetailsService {

    private final ApplicationUserDAO applicationUserDAO;

    public ApplicationUserService(ApplicationUserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }


    @Override
    public ApplicationUser registerNewUser(ApplicationUserRequest user) {
        return applicationUserDAO.registerNewUser(user.toApplicationUser());
    }

    @Override
    public ApplicationUser findUserById(UUID userId) {
        return applicationUserDAO.findUserById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id %s not found", userId)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDAO.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }
}
