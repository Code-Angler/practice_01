package com.spring.practice_01.web;

import com.spring.practice_01.service.posts.PostsService;
import com.spring.practice_01.web.dto.PostsResponseDto;
import com.spring.practice_01.web.dto.PostsSaveRequestDto;
import com.spring.practice_01.web.dto.PostsUpdateRequestDto;
import com.spring.practice_01.web.dto.isLockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/isLock/{id}")
    public isLockResponseDto isLock(@PathVariable Long id) {
        return postsService.isLock(id);
    }

    @PostMapping("/api/v1/posts/imWriting/{id}")
    public void imWriting(@PathVariable Long id) {
        postsService.imWriting(id);
    }

//    @PostMapping("/api/v1/posts/LockManager/{id}")
//    public void lockManager(@PathVariable Long id){
//        postsService.lockManager(id);
//    }

    @PostMapping("/api/v1/test/{id}")
    public void some(@PathVariable Long id) throws InterruptedException {

        System.out.println("LockManager 컨트롤러 시작");
        postsService.test(id);
        System.out.println("LockManager 컨트롤러 끝, 응답함");
    }

    @GetMapping("/api/v1/test/async")
    public void asyncTest() throws InterruptedException {
        postsService.logger();
        System.out.println("컨트롤러");
    }

    @GetMapping("/api/v1/test/test2")
    public void asyncTest2() {
        System.out.println("async");
    }
}

