package com.Igor.SpringMPS.security;

import com.Igor.SpringMPS.data.UserRepository;
import com.Igor.SpringMPS.dto.LocalUser;
import com.Igor.SpringMPS.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private LocalUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {

        //User user;
        User newUser = new User();

        OidcUserInfo oidcUserInfo = new OidcUserInfo(oidcUser.getAttributes());
        // see what other data from userRequest or oidcUser you need

        //Optional<User> userOptional = userRepository.findByEmail(oidcUserInfo.getEmail());
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(oidcUserInfo.getEmail()));
        //user = userRepository.findByEmail(oidcUserInfo.getEmail());
        if (!userOptional.isPresent()) {

            newUser.setEmail(oidcUserInfo.getEmail());
            newUser.setName(oidcUserInfo.getName());

            // set other needed data

            userRepository.save(newUser);
        }
        else {
            newUser = userOptional.get();
        }
        //return oidcUser;
        //return LocalUser.create(userOptional, attributes, idToken, userInfo);
        return LocalUser.create(newUser, oidcUser.getAttributes(), oidcUser.getIdToken(),
                oidcUser.getUserInfo());
    }
}
