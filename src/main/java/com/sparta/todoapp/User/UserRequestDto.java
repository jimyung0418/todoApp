package com.sparta.todoapp.User;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @Pattern(regexp = "[a-z0-9]{4,10}$", message = "username은 최소 4자 이상, 10자 이하, 알파벳 소문자, 숫자로 구성되어야 합니다.")
    private String username;
    @Pattern(regexp = "[a-zA-Z0-9]{8,15}$", message = "password는 최소 8자 이상, 15자 이하, 알파벳 대소문자, 숫자로 구성되어야 합니다.")
    private String password;
}
