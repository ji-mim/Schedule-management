package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.UserResponseDto;

import java.time.LocalDateTime;

public interface UserService {

    UserResponseDto saveUser(String email, String name, LocalDateTime createdAt, LocalDateTime updatedAt);


    UserResponseDto updateUserName(String email, String username, LocalDateTime updatedAt);

    UserResponseDto findUser(String email);
}
