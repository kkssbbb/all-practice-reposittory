package com.crud.guestbook.service;

import com.crud.guestbook.dto.GuestBookDto;
import com.crud.guestbook.dto.PageRequestDto;
import com.crud.guestbook.dto.PageResultDto;
import com.crud.guestbook.entity.GuestBook;
import com.crud.guestbook.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public interface GuestBookService {
    Long register(GuestBookDto dto);

    PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto);

    //읽기
    GuestBookDto read(Long gno);
    //수정
    void modify(GuestBookDto guestBookDto);
    //삭제
    void remove(Long gno);

     BooleanBuilder getSearch(PageRequestDto requestDTO);


    // dto -> entity 변환 메서드
    default GuestBook dtoEntity(GuestBookDto guestBookDto){


        GuestBook guestBookEntity = GuestBook.builder()
                .gno(guestBookDto.getGno())
                .title(guestBookDto.getTitle())
                .content(guestBookDto.getContent())
                .writer(guestBookDto.getContent())
                .build();

        return guestBookEntity;
    }

    // entity -> dto 변환 메서드
    default GuestBookDto entityToDto(GuestBook entity) {

        GuestBookDto dto = GuestBookDto.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return  dto;
    }
}
