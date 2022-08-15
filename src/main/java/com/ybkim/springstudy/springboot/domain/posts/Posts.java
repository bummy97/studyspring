package com.ybkim.springstudy.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter //클래스 내 모든 필드의 getter 메소드를 자동생성
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타냄
public class Posts {

    @Id //해당 테이블의 PK필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙을 나타냄, 스프링 2.0에서는 GenerationType.IDENTITY를 추가해야만 auto increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false) //테이블 컬럼을 나타냄, 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
