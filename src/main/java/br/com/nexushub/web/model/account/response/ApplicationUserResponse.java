package br.com.nexushub.web.model.account.response;

import br.com.nexushub.usecases.account.model.ApplicationUser;

import java.util.UUID;

public class ApplicationUserResponse {

    private UUID id;
    private String name;
    private String username;

    private ApplicationUserResponse(UUID id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public static ApplicationUserResponse createFromApplicationUser(ApplicationUser applicationUser){
        return new ApplicationUserResponse(
                applicationUser.getId(),
                applicationUser.getName(),
                applicationUser.getUsername()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ApplicationUserResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
