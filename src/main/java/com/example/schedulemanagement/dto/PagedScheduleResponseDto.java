package com.example.schedulemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PagedScheduleResponseDto {

    private Long id;

    private String userEmail;

    private String username;

    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
