package com.muscleduck.service;

import com.muscleduck.dto.BoardDTO;
import com.muscleduck.dto.PageRequestDTO;
import com.muscleduck.dto.PageResultDTO;
import com.muscleduck.entity.BoardEntity;
import com.muscleduck.entity.UserEntity;
import com.muscleduck.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImp implements BoardService{

     private final BoardRepository boardRepository; //자동주입 final


    //등록 구현
    @Override
    public Long register(BoardDTO boardDTO) {

        log.info("등록호출  " + boardDTO);

        BoardEntity boardEntity = dtoToEntity(boardDTO);
        //파라미터로 받은 보드 DTO를 dtoToEntity()로 변황하여 엔티티 타입으로 저장

        boardRepository.save(boardEntity);


        return boardEntity.getBno();
    }


    //목록 처리 구현
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info("getList() 호출 로그 , pageRequestDTO = "+ pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDto((BoardEntity) en[0],(UserEntity) en[1], (Long) en[2] ));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable((Sort.by("bno").descending())));


        return new PageResultDTO<>(result, fn);
    }
}
