package com.internet_forum.springboot.service;

import com.internet_forum.springboot.model.User;

public interface UserService {

    public void registerNewUser(User user);
    User findByUsername(String username);
}

