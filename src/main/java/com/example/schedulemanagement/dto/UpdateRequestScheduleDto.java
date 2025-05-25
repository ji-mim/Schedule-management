package com.example.schedulemanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UpdateRequestScheduleDto {

    @NotBlank(message = "password는 필수 값 입니다.")
    private String password;

    @Email(message = "옳바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수값 입니다.")
    private String userEmail;

    @NotBlank(message = "할일은 필수값 입니다.")
    @Size(max = 200, message = "200자를 넘을 수 없습니다.")
    private String contents;
}
