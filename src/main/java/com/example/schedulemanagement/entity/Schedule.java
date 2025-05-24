package com.example.schedulemanagement.entity;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {

    private final Long id;

    private final String password;

    private final String userEmail;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;


    public Schedule(Long id, String password, String userEmail, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.password = password;
        this.userEmail = userEmail;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Schedule(String password, String userEmail, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = null; // id를 final로 만들기 위해 필요함
        this.password = password;
        this.userEmail = userEmail;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
