package com.spring.practice_01.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String username;

    public UserResponseDto(String username) {
        this.username = username;
    }
}
