package br.com.nexushub.web.model.account.response;

import br.com.nexushub.usecases.account.model.ApplicationUser;

import java.util.UUID;

public record ApplicationUserResponse(UUID id, String name, String username) {

    public ApplicationUserResponse(UUID id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public static ApplicationUserResponse createFromApplicationUser(ApplicationUser user) {
        return new ApplicationUserResponse(
                user.getId(),
                user.getName(),
                user.getUsername()
        );
    }
}
