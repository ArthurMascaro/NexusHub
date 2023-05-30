package br.com.nexushub.usecases.account;

import br.com.nexushub.usecases.account.gateway.ApplicationUserDAO;
import br.com.nexushub.usecases.account.model.ApplicationUser;
import br.com.nexushub.web.model.account.request.ApplicationUserRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplicationUserCRUDimpl implements ApplicationUserCRUD {

    private final ApplicationUserDAO applicationUserDAO;

    public ApplicationUserCRUDimpl(ApplicationUserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }
    @Override
    public ApplicationUser registerNewUser(ApplicationUserRequest user) {
        return applicationUserDAO.registerNewUser(user.toApplicationUser());
    }

}
