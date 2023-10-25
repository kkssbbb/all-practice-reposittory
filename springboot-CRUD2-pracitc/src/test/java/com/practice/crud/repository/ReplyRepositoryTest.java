package com.practice.crud.repository;

import com.practice.crud.entity.Board;
import com.practice.crud.entity.Member;
import com.practice.crud.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    ReplyRepository repository;

    @Test
    @DisplayName("댓글 생성 테스트")
    void createReply(){

        for (int i = 0; i < 100; i++) {

            Long bno = (long)(Math.random()*100 +1);
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("댓글"+i)
                    .board(board)
                    .replyer("홍길동")
                    .build();

            repository.save(reply);

        }
    }
    @Test
    @DisplayName("댓글 삭제 테스트")
    void testRemove(){
        Long bno = 10L;
        repository.delteByBno(@Param("10l"));
    }


}