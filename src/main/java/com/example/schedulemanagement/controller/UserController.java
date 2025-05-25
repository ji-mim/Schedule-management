package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ChangeUserNameReqeustDto;
import com.example.schedulemanagement.dto.CreateRequestUserDto;
import com.example.schedulemanagement.dto.UserResponseDto;
import com.example.schedulemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{email}")
    ResponseEntity<UserResponseDto> findUser(@PathVariable String email) {
        UserResponseDto userResponseDto = userService.findUser(email);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{email}")
    ResponseEntity<UserResponseDto> updateUserName(
            @PathVariable String email,
            @RequestBody ChangeUserNameReqeustDto reqeustDto) {

        UserResponseDto userResponseDto = userService.updateUserName(email, reqeustDto.getUsername(), LocalDateTime.now());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
