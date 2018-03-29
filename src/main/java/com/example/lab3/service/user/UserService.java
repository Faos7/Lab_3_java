package com.example.lab3.service.user;


import com.example.lab3.domain.security.User;
import com.example.lab3.domain.security.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}