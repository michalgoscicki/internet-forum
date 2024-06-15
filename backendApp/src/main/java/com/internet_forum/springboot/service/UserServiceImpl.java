package com.internet_forum.springboot.service;


import com.internet_forum.springboot.Exceptions.UserDoNotExist;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.repository.RoleRepository;
import com.internet_forum.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    // w wolnej chwili mozna poprawic
    @Override
    public UserEntity updateUser(UserEntity updatedUser) {
        Optional<UserEntity> existingUser = userRepository.findById(updatedUser.getId());
        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();

            user.setId(existingUser.get().getId());
            user.setEmail(updatedUser.getEmail());
            user.setName(updatedUser.getName());
            user.setPassword(user.getPassword());
            return userRepository.save(user);
        } else {
            throw new UserDoNotExist("User not found with id:" + updatedUser.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.getRoles().clear();
            userRepository.save(user);
            userRepository.deleteById(id);
        } else {
            throw new UserDoNotExist("User not found with id: " + id);
        }
    }


    @Override
    public Optional<UserEntity> getUser(int id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id);
        } else {
            throw new UserDoNotExist("User not found with id: " + id);
        }
    }


    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}