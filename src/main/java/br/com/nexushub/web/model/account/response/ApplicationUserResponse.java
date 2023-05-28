package br.com.nexushub.web.model.account.response;

import br.com.nexushub.usecases.account.model.ApplicationUser;

public record ApplicationUserResponse(String name, String username) {

    public ApplicationUserResponse(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public static ApplicationUserResponse createFromApplicationUser(ApplicationUser user) {
        return new ApplicationUserResponse(
                user.getName(),
                user.getUsername()
        );
    }
}
