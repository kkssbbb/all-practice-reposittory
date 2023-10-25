package com.practice.crud.service;

import com.practice.crud.dto.BoardDTO;

import com.practice.crud.dto.PageRequestDTO;
import com.practice.crud.dto.PageResultDTO;
import com.practice.crud.entity.Board;
import com.practice.crud.entity.Member;

public interface BoardService {
    //게시글 등록은 BoardDTO를 파라미터로 받아 생성된 게시물의 번호를 반환하도록 한다.
    //BoardDTO -> BoardEntity 변환
    Long register(BoardDTO boardDTO);


    //목록 처리 getlist()
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    //게시글 조회 처리
    BoardDTO get(Long bno);

    //삭제기능
    void removeWithReplies(long bno);



    default Board dtoToEntity(BoardDTO boardDTO){

        Member member = Member.builder().email(boardDTO.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(member)
                .build();

        return  board;

    }

    //보드서비스 인터페이스에 추가하는 엔티티투디티오()
    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) //long 으로 나오므로 int 로 처리하도록
                .build();
        return boardDTO;
    }



}
