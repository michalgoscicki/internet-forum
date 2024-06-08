package com.internet_forum.springboot.controller;

import com.internet_forum.springboot.model.User;
import com.internet_forum.springboot.repository.UserRepository;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Użytkownik już jest zalogowany");
//        } else {
//            if (isValidLogin(username, password)) {
//                return ResponseEntity.ok("Login successful");
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//            }
//        }
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<String> getUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            String username = authentication.getName();
//            return ResponseEntity.ok("Zalogowany użytkownik: " + username);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nie jesteś zalogowany");
//        }
//    }
//
//    public boolean isValidLogin(String username, String password) {
//        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
//        int count = jdbcTemplate.query(query, Integer.class, username, password);
//        return count > 0;
//    }


}
