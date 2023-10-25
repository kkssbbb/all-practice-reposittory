package com.practice.crud.repository;

import com.practice.crud.entity.Board;
import com.practice.crud.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.event.annotation.AfterTestClass;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;



@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 더미 데이터")
    void createBoard() {

        for (int i = 0; i < 100; i++) {
            Member member = Member.builder().email("asd" + i + "@naver.com").build();

            Board board = Board.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);

        }

    }

    @Test
    @DisplayName("목록 화면 처리 보드 랑이터")
    void testReadWriter(){
        Object result = boardRepository.getBoardWriter(100L);

        Object[] arr = (Object[])result;

        System.out.println("===========================================");
        System.out.println("arr = " + Arrays.toString(arr));
    }


    @Test
    @DisplayName("목록 화면 처리 댓글 보드")
    @Transactional
    void testGetBoardWithReply(){
        List<Object[]> result = boardRepository.getBoardWithRply(3L);
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("댓글 카운트")
    void testReplyCount(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        }  );
    }

    @Test
    public void testRead(){
        Object result = boardRepository.getBoardByBno(100L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

}