package com.Igor.SpringMPS.data;

import com.Igor.SpringMPS.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User,Long> {
    User findByUsername(String username);
    User findByEmail(String username);
}
