package com.example.schedulemanagement.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UpdateRequestScheduleDto {

    private String password;

    private String username;

    private String contents;
}
