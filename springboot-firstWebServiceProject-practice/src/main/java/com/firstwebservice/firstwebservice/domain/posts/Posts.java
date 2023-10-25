package com.firstwebservice.firstwebservice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter                     //롬북어노테이션
@NoArgsConstructor         //롬북어노테이션  , 기본생성자 자동 추가
@Entity//JPA어노테이션 , 테이블과 링크될 클래스임을 나타냄
@Table(name = "Posts")
public class Posts extends BaseTimeEntity  {      //실제 DB의 테이블과 매칭될 클래스 Entity클래스라고도 한다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder   //롬북어노테이션
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        }
}
