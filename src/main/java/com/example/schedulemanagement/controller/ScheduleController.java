package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.CreateRequestScheduleDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.UpdateRequestScheduleDto;
import com.example.schedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody CreateRequestScheduleDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.saveSchedule(
                requestDto.getPassword(),
                requestDto.getContents(),
                requestDto.getUsername(),
                LocalDate.now(),
                LocalDate.now());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) LocalDate updatedAt
    ) {

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll(username, updatedAt);

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ScheduleResponseDto> findSchedule(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findScheduleById(id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateRequestScheduleDto requestDto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto.getPassword(), requestDto.getUsername(), requestDto.getContents(), LocalDate.now());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
