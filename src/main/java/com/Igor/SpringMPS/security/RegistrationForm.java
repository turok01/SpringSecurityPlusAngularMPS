package com.Igor.SpringMPS.security;

import com.Igor.SpringMPS.entities.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder){
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        return user;
        //return new User(username, passwordEncoder.encode(password));
    }
}
