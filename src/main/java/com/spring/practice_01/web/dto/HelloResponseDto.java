package com.spring.practice_01.web.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
