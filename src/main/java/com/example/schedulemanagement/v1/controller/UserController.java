package com.example.schedulemanagement.v1.controller;

import com.example.schedulemanagement.v1.dto.CreateRequestUserDto;
import com.example.schedulemanagement.v1.dto.UserResponseDto;
import com.example.schedulemanagement.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<UserResponseDto> saveUser(@RequestBody CreateRequestUserDto requestUserDto) {
        UserResponseDto userResponseDto = userService.saveUser(requestUserDto.getName());


        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}
