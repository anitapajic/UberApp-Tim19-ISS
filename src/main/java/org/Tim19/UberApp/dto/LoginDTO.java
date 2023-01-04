package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.User;

public class LoginDTO {
    private String username;
    private String password;

    public LoginDTO(String email, String password) {
        this.username = email;
        this.password = password;
    }

    public LoginDTO(){}

    public LoginDTO(User user){
        this(user.getUsername(), user.getPassword());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
