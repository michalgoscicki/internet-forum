package com.internet_forum.springboot.service;

import com.internet_forum.springboot.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    User saveUser(User user);

    void deleteUser(User user);

    List<User> getAllUsers();

    void deleteAllUsers();

    User updateUser(User updatedUser);

    void deleteById(Long id);

    Long length();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
    Optional<User> loginUser(String email, String rawPassword);

}
