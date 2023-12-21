package com.sparta.todoapp.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String password;

    public User(UserRequestDto userRequestDto, String encodedPassword) {
        this.username = userRequestDto.getUsername();
        this.password = encodedPassword;
    }
}
