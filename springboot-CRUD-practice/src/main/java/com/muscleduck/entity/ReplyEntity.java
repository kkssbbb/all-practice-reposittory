package com.muscleduck.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = "board")
public class ReplyEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rno;

    private String text;

    private String replyer;

    @ManyToOne //연관관계 지정
    private BoardEntity board;

}
