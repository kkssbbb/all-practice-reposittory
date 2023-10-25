package com.crud.guestbook.repository;

import com.crud.guestbook.entity.GuestBook;
import com.crud.guestbook.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestBookRepositoryTest {

    @Autowired
    GuestBookRepository guestBookRepository;


    @Test
    public void createTest() {
        for (int i = 0; i <= 300; i++) {

            GuestBook guestBook = GuestBook.builder().title("테스트제목" + i).content("테스트 내용" + i).writer("테스트 글쓴이" + i).build();
            guestBookRepository.save(guestBook);
        }
    }

    @Test
    public void updateTest() {
        Optional<GuestBook> result = guestBookRepository.findById(1L);

        if (result.isPresent()) {
            GuestBook guestBook = result.get();

            guestBook.changeTitle("제목변경");
            guestBook.changeContent("내용변경");

            guestBookRepository.save(guestBook);
        }

    }


    @Test
    @DisplayName("querydsl 단일 항목 검색 테스트")
    public void testQuery1() {
        //PageRequest.페이징(메서드 1페이지에, 10개씩 페이징 , "gno" 를 정렬)
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        //동적쿼리를 이용할뗀 QEntity 사용 ,동적쿼리를 이용해 데이터를 찾을 엔티티
        QGuestBook qGuestBook = QGuestBook.guestBook;

        //where 조건을 넣어준다.
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        //title like %5% 라는  조건문을 만들어주는 코드
        BooleanExpression expression = qGuestBook.title.contains("5");

        //where + title like %5% 결합
        booleanBuilder.and(expression);

        //QuerydslPredicateExecutor의 findAll을 이용해서 페이징과 검색
        Page<GuestBook> result = guestBookRepository.findAll(booleanBuilder, pageable);


        result.stream().forEach(GuestBook -> System.out.println("GuestBook = " + GuestBook));
    }

    @Test
    @DisplayName("querydsl 다중 항목 검색 테스트")
    public void queryTest(){

        //검색할 키워드
        String keyword = "1";

        Pageable pageable = PageRequest.of(0,5 ,Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // title like %1$
        BooleanExpression exTitle = qGuestBook.title.contains(keyword);
        // content like %1$
        BooleanExpression exContent = qGuestBook.content.contains(keyword);
        // title like %1$ or content like %1$
        BooleanExpression exAll = exTitle.or(exContent);

        booleanBuilder.and(exAll);

        booleanBuilder.and(qGuestBook.gno.gt(0L));

        Page<GuestBook> result = guestBookRepository.findAll(booleanBuilder, pageable);

        result.stream().forEach(GuestBook -> System.out.println("GuestBook = " + GuestBook));



    }
}