package com.example.schedulemanagement.v1.repository;

import com.example.schedulemanagement.v1.dto.ScheduleResponseDto;
import com.example.schedulemanagement.v1.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto save(Schedule schedule);

    List<ScheduleResponseDto> findAll(String username, LocalDate updatedAt);

    ScheduleResponseDto findByIdOrElseThrow(Long id);
}
