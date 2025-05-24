package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.CreateRequestScheduleDto;
import com.example.schedulemanagement.dto.DeleteRequestScheduleDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.UpdateRequestScheduleDto;
import com.example.schedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // LV.1 시작
    @PostMapping
    ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody CreateRequestScheduleDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.saveSchedule(
                requestDto.getPassword(),
                requestDto.getContents(),
                requestDto.getUserEmail(),
                LocalDateTime.now(),
                LocalDateTime.now());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(
            @RequestParam(required = false) String userEmail,
            @RequestParam(required = false) LocalDate updatedAt
    ) {
        LocalDateTime updatedDateTime = updatedAt != null ? updatedAt.atStartOfDay() : null;
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll(userEmail, updatedDateTime);

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ScheduleResponseDto> findSchedule(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findScheduleById(id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    //LV.2 시작
    @PatchMapping("/{id}")
    ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateRequestScheduleDto requestDto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto.getPassword(), requestDto.getUserEmail(), requestDto.getContents(), LocalDateTime.now());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                        @RequestBody DeleteRequestScheduleDto requestScheduleDto) {
        scheduleService.deleteSchedule(id, requestScheduleDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
