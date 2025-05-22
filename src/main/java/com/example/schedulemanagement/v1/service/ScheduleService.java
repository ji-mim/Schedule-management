package com.example.schedulemanagement.v1.service;

import com.example.schedulemanagement.v1.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(String password, String contents, String username, LocalDate createdAt, LocalDate updatedAt);

    List<ScheduleResponseDto> findAll(String username, LocalDate updatedAt);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String password, String username, String contents, LocalDate updatedAt);

    void deleteSchedule(Long id);
}
