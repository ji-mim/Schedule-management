package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    ResponseEntity<ScheduleResponseDto> saveSchedule(@Validated @RequestBody CreateRequestScheduleDto requestDto) {
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
            @Validated @RequestBody UpdateRequestScheduleDto requestDto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto.getPassword(), requestDto.getUserEmail(), requestDto.getContents(), LocalDateTime.now());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                        @Validated @RequestBody DeleteRequestScheduleDto requestScheduleDto) {
        scheduleService.deleteSchedule(id, requestScheduleDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/page")
    ResponseEntity<PagingResponse<PagedScheduleResponseDto>> findAllScheduleByPage(
            @RequestParam(required = false) String userEmail,
            @RequestParam(required = false) LocalDate updatedAt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        LocalDateTime updatedDateTime = updatedAt != null ? updatedAt.atStartOfDay() : null;

        List<PagedScheduleResponseDto> scheduleResponseDtoList = scheduleService.findPagedSchedules(userEmail, updatedDateTime, page, size);

        Long totalElements = scheduleService.countSchedules(userEmail, updatedDateTime);

        PagingResponse<PagedScheduleResponseDto> pagingResponse = new PagingResponse<>(scheduleResponseDtoList, page, size, totalElements);


        return new ResponseEntity<>(pagingResponse, HttpStatus.OK);
    }

}
