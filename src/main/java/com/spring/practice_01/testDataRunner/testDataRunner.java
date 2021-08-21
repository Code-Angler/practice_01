package com.spring.practice_01.testDataRunner;

import com.spring.practice_01.domain.posts.Posts;
import com.spring.practice_01.domain.posts.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class testDataRunner implements ApplicationRunner {

    @Autowired
    PostsRepository postsRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        postsRepository.save(Posts.builder()
                .author("작성자")
                .content("내용")
                .title("타이틀")
                .build());
    }
}
