package com.Igor.SpringMPS.services;

import com.Igor.SpringMPS.data.UserRepository;
import com.Igor.SpringMPS.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{
        User userFindByUserName = userRepository.findByUsername(username);
        User userFindByName = userRepository.findByName(username);
        User userFindByGoogleUsername = userRepository.findByGoogleUsername(username);
        User userFindByGoogleName = userRepository.findByGoogleName(username);

        if (userFindByUserName!=null) {return userFindByUserName;}

        if (userFindByName!=null) {return userFindByName;}
        if(userFindByGoogleUsername != null)
        {
            return userFindByGoogleUsername;
        }

        if(userFindByGoogleName != null)
        {
            return userFindByGoogleName;
        }


        throw new UsernameNotFoundException(
                "User '" + username + "' not found");

    }
}
