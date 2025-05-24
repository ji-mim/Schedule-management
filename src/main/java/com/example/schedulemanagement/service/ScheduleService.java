package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(String password, String contents, String username, LocalDateTime createdAt, LocalDateTime updatedAt);

    List<ScheduleResponseDto> findAll(String username, LocalDateTime updatedAt);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String password, String username, String contents, LocalDateTime updatedAt);

    void deleteSchedule(Long id, String password);
}
