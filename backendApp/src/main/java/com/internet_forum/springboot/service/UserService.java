package com.internet_forum.springboot.service;

import com.internet_forum.springboot.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {


    UserEntity updateUser(UserEntity updatedUser);


    void deleteById(int id);

    Optional<UserEntity> getUser(int id);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> findByEmail(String email);

    boolean isAdmin(int id);
}
