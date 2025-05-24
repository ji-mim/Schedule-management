package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {

    private final String email;

    private final String name;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

}

