package com.example.buslogic.model.dto;

import com.example.buslogic.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthDTO {
    @NotNull private String username;
    @NotNull private String password;
    @NotNull private String email;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }
}
