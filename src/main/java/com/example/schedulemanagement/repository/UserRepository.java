package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UserResponseDto;
import com.example.schedulemanagement.entity.User;

public interface UserRepository {

    UserResponseDto save(User user);
}
