package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.CreateRequestUserDto;
import com.example.schedulemanagement.dto.UserResponseDto;
import com.example.schedulemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<UserResponseDto> saveUser(@Validated @RequestBody CreateRequestUserDto requestUserDto) {
        UserResponseDto userResponseDto = userService.saveUser(requestUserDto.getEmail(), requestUserDto.getName(), LocalDateTime.now(), LocalDateTime.now());

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}
