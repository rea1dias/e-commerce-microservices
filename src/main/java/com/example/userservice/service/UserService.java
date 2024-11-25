package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.model.UserDto;

public interface UserService {
    boolean register(UserDto dto);
    String login(String username, String password);
    User findByUsername(String username);
    User getCurrentUser();
}
