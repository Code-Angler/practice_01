package com.spring.practice_01.web.dto;

import com.spring.practice_01.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.author = entity.getTitle();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
    }
}
