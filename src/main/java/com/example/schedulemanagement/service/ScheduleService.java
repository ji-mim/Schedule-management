package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.PagedScheduleResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(String password, String contents, String userEmail, LocalDateTime createdAt, LocalDateTime updatedAt);

    List<ScheduleResponseDto> findAll(String userEmail, LocalDateTime updatedAt);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String password, String userEmail, String contents, LocalDateTime updatedAt);

    void deleteSchedule(Long id, String password);

    List<PagedScheduleResponseDto> findPagedSchedules(String userEmail, LocalDateTime updatedAt, int page, int size);

    Long countSchedules(String userEmail, LocalDateTime updatedAt);
}
