package com.spring.practice_01.service;

import com.spring.practice_01.domain.posts.Posts;
import com.spring.practice_01.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@Service
public class LockManagerService {

    private final PostsRepository postsRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void transaction1(Long postsId){
        em.clear();
        Posts posts = postsRepository.findById(postsId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+1L)
        );
        System.out.println("혹시 모르니 닫는 과정에서,");
        System.out.println("Lock :" + posts.getLock().toString() + "->"+ Boolean.TRUE.toString() );
        System.out.println("Pending :" + posts.getPending().toString() + "->"+ Boolean.TRUE.toString() );
        posts.setLock(true);
        posts.setPending(true);
    }

    @Transactional
    public void DeLock(Long postsId){
        em.clear();
        Posts posts = postsRepository.findById(postsId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+1L)
        );
        System.out.println("Lock :" + posts.getLock().toString() + "->"+ Boolean.FALSE.toString() );
        System.out.println("Pending :" + posts.getPending().toString() + "->"+ Boolean.FALSE.toString() );
        posts.setLock(false);
        posts.setPending(false);
    }

    @Transactional
    public void SeemsThatNoBodyIsWriting(Long postsId){
        em.clear();
        Posts posts = postsRepository.findById(postsId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+1L)
        );
        System.out.println("Pending :" + posts.getPending().toString() + "->"+ Boolean.FALSE.toString() );
        posts.setPending(false);
    }

    @Transactional
    public Boolean IsAnyOneWriting(Long postId){
        em.clear();
        Posts posts = postsRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+1L)
        );
        if(posts.getPending() == true){
            System.out.println("누군가 쓰고있다고 서버가 알아차림");
            return true;
        }else{
            System.out.println("누군가 쓰고있지 않다고 서버가 알아차림");
            return false;
        }
    }
}
