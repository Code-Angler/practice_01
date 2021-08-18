package com.spring.practice_01.web.dto;

import com.spring.practice_01.domain.posts.Posts;
import lombok.Getter;

//ResponseDto의 표준임. getter와 builder패턴으로 받음. 반대로, requestDto의 경우, getter와 no args constructor가 존재해야 jackson으로 json을 받을 수 있음.
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

    public static PostsResponseDtoBuilder builder() {
        return new PostsResponseDtoBuilder();
    }

    public static class PostsResponseDtoBuilder {
        private Posts entity;

        PostsResponseDtoBuilder() {
        }

        public PostsResponseDtoBuilder entity(Posts entity) {
            this.entity = entity;
            return this;
        }

        public PostsResponseDto build() {
            return new PostsResponseDto(entity);
        }

        public String toString() {
            return "PostsResponseDto.PostsResponseDtoBuilder(entity=" + this.entity + ")";
        }
    }
}
