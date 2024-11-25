package com.example.userservice.mapper;

import com.example.userservice.entity.User;
import com.example.userservice.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto dto);
    UserDto toDto(User entity);
}
