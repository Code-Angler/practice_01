package com.spring.practice_01.web;

import com.spring.practice_01.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponseDto hello(@RequestParam("name") String name ,@RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    }
}
