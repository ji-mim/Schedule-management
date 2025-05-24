package com.example.schedulemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ChangeUserNameReqeustDto {

    private String password;

    private String userEmail;

    private String username;

    private String contents;

}
