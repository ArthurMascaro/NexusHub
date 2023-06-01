package br.com.nexushub.web.controller;

import br.com.nexushub.usecases.account.ApplicationUserCRUD;
import br.com.nexushub.usecases.account.model.ApplicationUser;
import br.com.nexushub.web.model.account.request.ApplicationUserRequest;
import br.com.nexushub.web.model.account.response.ApplicationUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationUserController {

    private final ApplicationUserCRUD applicationUserCRUD;

    public ApplicationUserController(ApplicationUserCRUD applicationUserCRUD) {
        this.applicationUserCRUD = applicationUserCRUD;
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationUserResponse> createUser(@RequestBody ApplicationUserRequest applicationUserRequest) {
        ApplicationUser applicationUser = applicationUserCRUD.registerNewUser(applicationUserRequest);
        return ResponseEntity.ok(ApplicationUserResponse.createFromApplicationUser(applicationUser));
    }
}
