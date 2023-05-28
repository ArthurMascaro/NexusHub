package br.com.nexushub.usecases.account.gateway;

import br.com.nexushub.usecases.account.model.ApplicationUser;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationUserDAO {

    ApplicationUser registerNewUser(ApplicationUser user);

    Optional<ApplicationUser> findUserById(UUID id);

    Optional<ApplicationUser> findUserByUsername(String username);


}
