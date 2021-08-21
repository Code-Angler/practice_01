package com.spring.practice_01.domain.posts;

import com.spring.practice_01.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
//필자는 어노테이션 순서를 주요 어노테이션을 클래스에 가깝게 둡니다.
//따라서, Entity라는 어노테이션을 class에 더 가깝게 둡니다.
//Enitity는 테이블과 링크 될 클래스임을 나타내는 어노테이션입니다.
//기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭합니다.
//ex) SalesManager.java -> sales_manager table
//@Setter // Entity 클래스에서는 절대 Setter 메소드를 만들지 않습니다. 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는
// 세터가 아닌 메소드를 추가해야 합니다. (그러나 세터는 그와 동일하게 기능합니다.)
@DynamicUpdate // 더티체킹에 관한 내용이며, 변경사항
public class Posts extends BaseTimeEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) // Id 생성전략에 대한 내용을 다룹니다. https://jojoldu.tistory.com/295
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됩니다.
    // 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.
    // 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶은 경우에 사용됩니다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String author;

    @Column
    private Boolean lock;

    @Column
    private Boolean pending;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
        this.lock = false;
        this.pending = false;
    }

    //setter (update)
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
