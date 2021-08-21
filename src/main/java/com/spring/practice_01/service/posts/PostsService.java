package com.spring.practice_01.service.posts;

import com.spring.practice_01.domain.posts.Posts;
import com.spring.practice_01.domain.posts.PostsRepository;
import com.spring.practice_01.service.LockManagerService;
import com.spring.practice_01.web.dto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final LockManagerService lockManagerService;

    @PersistenceContext
    EntityManager em;


    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id)
        );

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id)
        );

        return PostsResponseDto.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(e->new PostsListResponseDto(e))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id)
        );

        postsRepository.delete(posts);
    }

    @Transactional
    public isLockResponseDto isLock(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id)
        );

        if(posts.getLock()){
            return new isLockResponseDto(Boolean.TRUE);
        }else{
            return new isLockResponseDto(Boolean.FALSE);
        }
    }

    @Transactional
    public void imWriting(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id)
        );
        System.out.println("다른 api에서 누군가 노트를 쓰고 있다고 알림");
        posts.setPending(true);
    }

    public void test(Long id) throws InterruptedException {
        System.out.println("LockManager 서비스 시작.");
        System.out.println("혹시 모르니 LockManager에서도 닫고 출발함.");
        lockManagerService.transaction1(id);
        while(true){
            System.out.println("쓰레드 자러감");
            Thread.sleep(10000);
            System.out.println("쓰레드 일어남");
            if(lockManagerService.IsAnyOneWriting(id)){
                System.out.println("누군가 쓰고 있으니, 서버는 다음에 자고 일어나서도 쓰고 있는지 알아보기 위해, 노트를 아무도 안쓰고 있다고 바꿈");
                lockManagerService.SeemsThatNoBodyIsWriting(id);
            }else{
                System.out.println("자고 일어나서도 아직 아무도 안쓰고 있으니 서버가 열어버림");
                lockManagerService.DeLock(id);
                break;
            }
        }
    }

    @Async
    public void logger() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("서비스");
    }

}
