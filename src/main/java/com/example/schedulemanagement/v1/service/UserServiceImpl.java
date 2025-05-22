package com.example.schedulemanagement.v1.service;

import com.example.schedulemanagement.v1.dto.UserResponseDto;
import com.example.schedulemanagement.v1.entity.User;
import com.example.schedulemanagement.v1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponseDto saveUser(String name) {
        User user = new User(name);

        return userRepository.save(user);

    }
}
