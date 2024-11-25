package com.example.userservice.service.impl;

import com.example.userservice.config.JwtUtil;
import com.example.userservice.entity.User;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper mapper;
    private final JwtUtil jwt;

    @Override
    public boolean register(UserDto dto) {
        if (repository.existsByEmail(dto.getEmail()) || repository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("User already exists");
        }
        User user = mapper.toEntity(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return true;
    }

    @Override
    public String login(String username, String password) {
        User user = findByUsername(username);
        if (user == null || !encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwt.generateToken(user.getUsername());
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("Authentication required");
        } else {
            return (User) authentication.getPrincipal();
        }

    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }



}
