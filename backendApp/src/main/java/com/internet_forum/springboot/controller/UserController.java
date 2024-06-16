package com.internet_forum.springboot.controller;

import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.repository.UserRepository;
import com.internet_forum.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getAllUsers();

    }
    @GetMapping("/{id}")
    public Optional<UserEntity> getUser(@PathVariable int id) {
        return userService.getUser(id);
    }


    //TO DO trzeba podawac wszystkie dane
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable int id, @Valid @RequestBody UserEntity user){
        if(userRepository.existsById(id)){
            UserEntity updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }



    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable String email){
        Optional<UserEntity> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }







}
