package com.example.schedulemanagement.v1.repository;

import com.example.schedulemanagement.v1.dto.UserResponseDto;
import com.example.schedulemanagement.v1.entity.User;

public interface UserRepository {

    UserResponseDto save(User user);
}
