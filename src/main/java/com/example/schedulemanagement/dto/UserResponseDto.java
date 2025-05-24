package com.example.schedulemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class UserResponseDto {

    private String email;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
