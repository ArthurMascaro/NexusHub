package br.com.nexushub.web.controller;

import br.com.nexushub.usecases.account.ApplicationUserCRUD;
import br.com.nexushub.usecases.account.model.ApplicationUser;
import br.com.nexushub.web.model.account.request.ApplicationUserRequest;
import br.com.nexushub.web.model.account.response.ApplicationUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserApplicationController {

    private final ApplicationUserCRUD applicationUserCRUD;

    public UserApplicationController(ApplicationUserCRUD applicationUserCRUD) {
        this.applicationUserCRUD = applicationUserCRUD;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody ApplicationUserRequest applicationUserRequest) {
        ApplicationUser applicationUser = applicationUserCRUD.registerNewUser(applicationUserRequest);

        return ResponseEntity.ok(
                ApplicationUserResponse.createFromApplicationUser(applicationUser)
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApplicationUserResponse> findUserById(@PathVariable UUID userId) {
        ApplicationUser applicationUser = applicationUserCRUD.findUserById(userId);

        return ResponseEntity.ok(ApplicationUserResponse.createFromApplicationUser(applicationUser));
    }
}
