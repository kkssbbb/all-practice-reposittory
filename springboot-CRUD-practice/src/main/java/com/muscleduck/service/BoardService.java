package com.muscleduck.service;

import com.muscleduck.dto.BoardDTO;
import com.muscleduck.dto.PageRequestDTO;
import com.muscleduck.dto.PageResultDTO;
import com.muscleduck.entity.BoardEntity;
import com.muscleduck.entity.UserEntity;

public interface BoardService {

    //등록 기능
    Long register(BoardDTO boardDTO);


    //목록 처리 기능
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);


    //DTO ->Entity 타입변환 기능
    default BoardEntity dtoToEntity(BoardDTO boardDTO){

        UserEntity user = UserEntity.builder().uno(boardDTO.getUno()).build();

        BoardEntity boardEntity = BoardEntity.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(user)
                .build();

        return boardEntity;
    }

    //EntityToDto 타입 변환
    default BoardDTO entityToDto(BoardEntity boardEntity, UserEntity userEntity, Long replyCount){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(boardEntity.getBno())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .regDate(boardEntity.getCreateDate())
                .modDate(boardEntity.getModifiedDate())
                .writerEmail(userEntity.getEmail())
                .writerNickName(userEntity.getNickname())
                .replyCount(replyCount.intValue())//Long -> int 변환
                .build();

        return boardDTO;
    }
}
