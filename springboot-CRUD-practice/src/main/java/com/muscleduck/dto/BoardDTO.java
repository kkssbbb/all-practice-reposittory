package com.muscleduck.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private long bno;

    private long uno; // 작성자 고유번호 +축가 8/5

    private String title;

    private String content;

    private String writerNickName; //작성자 닉네임

    private String writerEmail; //작성자 이메일

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private int replyCount; //해당 게시글의 댓글 수

    // 추가 예정  private int viewsCount; 조회수
    // 추가 예정 private int likeCount; 좋아요 수
}
