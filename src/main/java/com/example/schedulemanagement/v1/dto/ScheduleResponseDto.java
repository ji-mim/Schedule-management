package com.example.schedulemanagement.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ScheduleResponseDto {

    private Long id;

    private String username;

    private String contents;

    private LocalDate createdAt;

    private LocalDate updatedAt;



}
