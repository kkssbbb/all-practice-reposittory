package com.practice.crud.service;

import com.practice.crud.dto.BoardDTO;

import com.practice.crud.dto.PageRequestDTO;
import com.practice.crud.dto.PageResultDTO;
import com.practice.crud.entity.Member;
import com.practice.crud.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest

class BoardServiceIMPTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Commit
    @Transactional
    @DisplayName("게시글 작")
    void testDtoToEnti성y() {

        BoardDTO boardDTO = BoardDTO.builder()
                .title("제목")
                .content("내용")
                .writerEmail("asd10@naver.com")
                .build();

        Long bno = boardService.register(boardDTO);

    }

    @Test
    @DisplayName("목록처리 테스트")
    void testList() {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]>result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO : result.getDtoList()) {
            System.out.println("==============================");
            System.out.println(boardDTO);
        }
    }

    @Test
    @DisplayName("게시물 조회")
    void testGet(){
        Member member = Member.builder()
                .name("홍길동")
                .email("test@naver.com")
                .password("1234")
                .build();
        memberRepository.save(member);

        Long bno = 1L;
        BoardDTO boardDTO = boardService.get(bno);

        System.out.println("boardDTO = " + boardDTO);
    }



}