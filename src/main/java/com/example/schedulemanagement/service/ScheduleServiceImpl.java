package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getUsername(), findSchedule.getContents(), findSchedule.getCreatedAt(), findSchedule.getUpdatedAt());


    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String password, String username, String contents, LocalDate updatedAt) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        //비밀번호 같은지 확인하는 로직
        if (!findSchedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is not same");
        }

        scheduleRepository.updateSchedule(id, username, contents, updatedAt);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getUsername(), findSchedule.getContents(), findSchedule.getCreatedAt(), findSchedule.getUpdatedAt());

    }

    @Override
    public void deleteSchedule(Long id) {
        // id가 존재하는지 확인
        scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.deleteSchedule(id);
    }
}
