package com.Igor.SpringMPS.security;

import com.Igor.SpringMPS.data.UserRepository;
import com.Igor.SpringMPS.entities.AuthProvider;
import com.Igor.SpringMPS.entities.Role;
import com.Igor.SpringMPS.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationControllerJavaRush {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Autowired
   //public RegistrationControllerJavaRush(UserRepository userRepository){this.userRepository=userRepository;}

    @GetMapping("/registrationRush")
    public String registration(){
        return "registrationRush";
    }

    @PostMapping("/registrationRush")
    //public String addUser(RegistrationForm registrationForm){
    public String addUser(String name, String username, String email, String password, String confirm){
        User user = new User();
        //username = name;
        user.setUsername(username);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Collections.singleton(Role.USER));
        user.setProvider(AuthProvider.LOCAL);
        userRepository.save(user);
        //userRepository.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
    /*public String addUser(String name, String username, String password){
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return "redirect:/login";
    }*/
}
