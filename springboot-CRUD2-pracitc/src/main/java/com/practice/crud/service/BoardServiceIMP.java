package com.practice.crud.service;

import com.practice.crud.dto.BoardDTO;
import com.practice.crud.dto.PageRequestDTO;
import com.practice.crud.dto.PageResultDTO;
import com.practice.crud.entity.Board;
import com.practice.crud.entity.Member;
import com.practice.crud.repository.BoardRepository;
import com.practice.crud.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceIMP implements BoardService{

    private final BoardRepository boardRepository; //자동주입 final
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board  = dtoToEntity(dto);

        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO(
                (Board)en[0],(Member)en[1],(Long)en[2]));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));


        return new PageResultDTO<>(result,fn);
    }

    //게시글 조회처리 구현
    @Override
    public BoardDTO get(Long bno) {

        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Member)arr[1], (Long)arr[2]);
    }

    // 삭제기능 구현, 트랜잭션 추가
    @Transactional
    @Override
    public void removeWithReplies(long bno) {

        //댓글 부터 삭제
        replyRepository.delteByBno(bno);

        boardRepository.deleteById(bno);

    }
}
