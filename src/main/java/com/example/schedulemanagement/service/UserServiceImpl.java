package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.UserResponseDto;
import com.example.schedulemanagement.entity.User;
import com.example.schedulemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto saveUser(String email, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        User user = new User(email, name, createdAt, updatedAt);

        return userRepository.save(user);

    }

    @Override
    public UserResponseDto findUser(String email) {
        User findUser = userRepository.findByIdOrElseThrow(email);

        return new UserResponseDto(email, findUser.getName(), findUser.getCreatedAt(), findUser.getUpdatedAt());
    }

    @Override
    public UserResponseDto updateUserName(String email, String username, LocalDateTime updatedAt) {
        User findUser = userRepository.findByIdOrElseThrow(email);

        userRepository.updateUserName(email, username, updatedAt);

        return new UserResponseDto(email, username, findUser.getCreatedAt(), updatedAt);
    }
}
