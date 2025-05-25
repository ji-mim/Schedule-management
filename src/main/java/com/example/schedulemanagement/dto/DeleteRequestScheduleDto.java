package com.example.schedulemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DeleteRequestScheduleDto {

    @NotBlank(message = "password는 필수값 입니다.")
    private String password;

}
