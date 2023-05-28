package br.com.nexushub.usecases.account;

import br.com.nexushub.usecases.account.model.ApplicationUser;
import br.com.nexushub.web.model.account.request.ApplicationUserRequest;

import java.util.UUID;

public interface ApplicationUserCRUD {

    ApplicationUser registerNewUser(ApplicationUserRequest user);

    ApplicationUser findUserById(UUID userId);

    /*ApplicationUser findUserByLogin(String login);*/


}
