package com.example.schedulemanagement.v1.service;

import com.example.schedulemanagement.v1.dto.ScheduleResponseDto;
import com.example.schedulemanagement.v1.entity.Schedule;
import com.example.schedulemanagement.v1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;


    @Override
    public ScheduleResponseDto saveSchedule(String password, String contents, String username, LocalDate createdAt, LocalDate updatedAt) {
        Schedule schedule = new Schedule(password, username, contents, createdAt, updatedAt);

        return scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAll(String username, LocalDate updatedAt) {

        return scheduleRepository.findAll(username, updatedAt);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        return scheduleRepository.findByIdOrElseThrow(id);
    }
}
