package com.muscleduck.repository;

import com.muscleduck.entity.BoardEntity;
import com.muscleduck.entity.ReplyEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {


    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @DisplayName("덧글 데이터 추가")
    void testReply(){

        for (int i = 0; i < 100; i++) {

            long bno = (long)(Math.random() *100) +1;

            BoardEntity board = BoardEntity.builder().bno(bno).build();

            ReplyEntity reply = ReplyEntity.builder()
                    .text("덧글 테스트"+ i)
                    .board(board)
                    .replyer("홍길동")
                    .build();

            replyRepository.save(reply);

        }

    }
}