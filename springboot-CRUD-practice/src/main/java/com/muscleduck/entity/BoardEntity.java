package com.muscleduck.entity;

import lombok.*;


import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "writer")
public class BoardEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bno;

    //제목
    private String title;
    //내용
    private String content;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY) //연관관계 지정
    private UserEntity writer;

    //조회수
    //수정일
    //생성일

}
