package com.example.schedulemanagement.v1.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateRequestScheduleDto {

    private String username;

    private String password;

    private String contents;

}
