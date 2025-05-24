package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.PagedScheduleResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;


    @Override
    public ScheduleResponseDto saveSchedule(String password, String contents, String userEmail, LocalDateTime createdAt, LocalDateTime updatedAt) {
        Schedule schedule = new Schedule(password, userEmail, contents, createdAt, updatedAt);

        return scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAll(String userEmail, LocalDateTime updatedAt) {

        return scheduleRepository.findAll(userEmail, updatedAt);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getUserEmail(), findSchedule.getContents(), findSchedule.getCreatedAt(), findSchedule.getUpdatedAt());


    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String password, String userEmail, String contents, LocalDateTime updatedAt) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        //비밀번호 같은지 확인하는 로직
        if (!findSchedule.getPassword().equals(password)) {
            log.warn("비밀번호 불일치: 입력 = {}, 실제 = {}", password, findSchedule.getPassword());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is not same");
        }

        scheduleRepository.updateSchedule(id, userEmail, contents, updatedAt);

        return new ScheduleResponseDto(id, userEmail, contents, findSchedule.getCreatedAt(), updatedAt);

    }

    @Override
    public void deleteSchedule(Long id, String password) {
        // id가 존재하는지 확인
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 비밀번호 같은지 확인
        if (!findSchedule.getPassword().equals(password)) {
            log.warn("비밀번호 불일치: 입력 = {}, 실제 = {}", password, findSchedule.getPassword());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is not same");

        }

        scheduleRepository.deleteSchedule(id);
    }

    @Override
    public List<PagedScheduleResponseDto> findPagedSchedules(String userEmail, LocalDateTime updatedAt, int page, int size) {
        return scheduleRepository.findPagedSchedules(userEmail, updatedAt, page, size);
    }
}
