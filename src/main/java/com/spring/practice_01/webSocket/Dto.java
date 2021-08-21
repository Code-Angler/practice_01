package com.spring.practice_01.webSocket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class Dto {

    private String name;

    public Dto(String name) {
        this.name = name;
    }
}
