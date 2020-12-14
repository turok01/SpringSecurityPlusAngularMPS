package com.Igor.SpringMPS.data;

import com.Igor.SpringMPS.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <User,Long> {
    User findByUsername(String username);
    User findByName(String name);
    User findByGoogleName(String googleName);
    User findByGoogleUsername(String googleUsername);
    //User findByEmail(String email);

    Optional<User> findByEmail(String email);
}
