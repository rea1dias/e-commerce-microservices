package com.example.userservice.controller;

import com.example.userservice.model.UserDto;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto dto) {
        try {
            boolean isRegistered = service.register(dto);
            if (isRegistered) {
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed.");
            }
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto dto) {
        try {
            String token = service.login(dto.getUsername(), dto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body("Successfully logged in");
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authentication failed.");
        }

    }




}
