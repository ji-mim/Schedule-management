package com.example.schedulemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleResponseDto {

    private Long id;

    private String username;

    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
