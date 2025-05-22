package com.example.schedulemanagement.v1.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Schedule {

    private Long id;

    private String password;

    private String username;

    private String contents;

    private LocalDate createdAt;

    private LocalDate updatedAt;


    public Schedule(String password, String username, String contents, LocalDate createdAt, LocalDate updatedAt) {
        this.password = password;
        this.username = username;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(LocalDate createdAt, LocalDate updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
