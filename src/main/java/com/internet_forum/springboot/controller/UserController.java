package com.internet_forum.springboot.controller;

import com.internet_forum.springboot.model.User;
import com.internet_forum.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUseryId(@PathVariable("id") long userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String userEmail) {
        return userService.findByEmail(userEmail)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @RequestBody User user) {
        return userService.findById(userId).map(savedUser -> {
            savedUser.setUsername(user.getUsername());
            savedUser.setEmail(user.getEmail());
            savedUser.setRole(user.getRole());

            User updatedUser = userService.updateUser(savedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userId) {
        userService.deleteById(userId);
        return new ResponseEntity<String>("User deleted successfully.", HttpStatus.OK);
    }

    @DeleteMapping("users")
    public ResponseEntity<String> deleteUser() {
        userService.deleteAllUsers();
        return new ResponseEntity<String>("All users deleted successfully", HttpStatus.OK);
    }

    @PostMapping("users/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
