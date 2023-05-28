package br.com.nexushub.web.model.account.request;

import br.com.nexushub.usecases.account.model.ApplicationUser;

public record ApplicationUserRequest(String name, String username, String password) {

    public ApplicationUserRequest(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public ApplicationUser toApplicationUser() {
        return ApplicationUser.createFromApplicationUser(name, username, password);
    }
}
