package com.crud.guestbook.service;

import com.crud.guestbook.dto.GuestBookDto;
import com.crud.guestbook.dto.PageRequestDto;
import com.crud.guestbook.dto.PageResultDto;
import com.crud.guestbook.entity.GuestBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestBookServiceImpTest {


    @Autowired
    GuestBookService guestBookService;

    @Test
    @DisplayName("dto -> entity Test")
    void register() {

        GuestBookDto guestBookDto = GuestBookDto.builder()

                .title("test")
                .content("testContent")
                .writer("testWriter")
                .build();

        System.out.println(guestBookService.register(guestBookDto));
    }

    @Test
    @DisplayName("목록처리 테스트")
    void listTest() {

        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDto<GuestBookDto, GuestBook> resultDto = guestBookService.getList(pageRequestDto);

        for(GuestBookDto guestBookDto : resultDto.getDtoList()){
            System.out.println("guestBookDto = " + guestBookDto);
        }
        System.out.println("========================================================");
        System.out.println("이전링크: "+resultDto.isPrev());
        System.out.println("다음링크: "+resultDto.isNext());
        System.out.println("TOTAL : "+ resultDto.getTotalPage());
    }



}