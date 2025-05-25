package com.example.schedulemanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateRequestUserDto {

    private String name;

    @Email(message = "옳바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수값 입니다.")
    private String email;

}
