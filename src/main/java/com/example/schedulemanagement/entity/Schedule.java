package com.example.schedulemanagement.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {

    private Long id;

    private String password;

    private String username;

    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Schedule(Long id, String password, String username, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Schedule(String password, String username, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.password = password;
        this.username = username;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
