package com.example.schedulemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UpdateRequestScheduleDto {

    private String password;

    private String userEmail;

    private String contents;
}
