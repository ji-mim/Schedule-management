package com.example.schedulemanagement.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {

    private String name;

    public UserResponseDto(String name) {
        this.name = name;
    }
}
