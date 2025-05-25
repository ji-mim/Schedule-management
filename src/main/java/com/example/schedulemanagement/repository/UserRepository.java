package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UserResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.User;

import java.time.LocalDateTime;

public interface UserRepository {

    UserResponseDto save(User user);

    User findByIdOrElseThrow(String email);

    void updateUserName(String email, String username, LocalDateTime updatedAt);
}
