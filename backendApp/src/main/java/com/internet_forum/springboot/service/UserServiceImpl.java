package com.internet_forum.springboot.service;


import com.internet_forum.springboot.model.User;
import com.internet_forum.springboot.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.internet_forum.springboot.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        private final PasswordEncoder passwordEncoder;

        public void registerNewUser(User user) {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.USER);
            userRepository.save(user);
        }

        public User findByUsername(String username) {
            return userRepository.findByUsername(username);
        }


}
