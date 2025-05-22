package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto save(Schedule schedule);

    List<ScheduleResponseDto> findAll(String username, LocalDate updatedAt);

    Schedule findByIdOrElseThrow(Long id);

    void updateSchedule(Long id, String username, String contents, LocalDate updatedAt);

    void deleteSchedule(Long id);
}
