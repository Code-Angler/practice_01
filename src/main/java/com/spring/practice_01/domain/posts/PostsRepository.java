package com.spring.practice_01.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 보통 ibatis나 MyBatis 등에서 Dao(Data Access Object)라고 불리는 DB Layer 접근자입니다.
// JPA에선 Repository라고 부르며 인터페이스로 생성합니다. 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입> 을 상속하면 기본적인
// CRUD 메소드가 자동으로 생성됩니다.

//주의해야할 점은, Entity클래스와 기본 Entity Repository는 함께 위치해야 합니다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
