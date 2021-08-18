package com.spring.practice_01.web;

import com.spring.practice_01.config.auth.Dto.SessionUser;
import com.spring.practice_01.config.auth.LoginUser;
import com.spring.practice_01.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class UserApiController {

    @GetMapping("/api/user")
    public UserResponseDto getUser(@LoginUser SessionUser user){
        return new UserResponseDto(user.getName());
    }
}
