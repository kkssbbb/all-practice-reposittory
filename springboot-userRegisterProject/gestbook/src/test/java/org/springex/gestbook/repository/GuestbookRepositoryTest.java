package org.springex.gestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springex.gestbook.entity.GuestBook;
import org.springex.gestbook.entity.QGuestBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;



@SpringBootTest
public class GuestbookRepositoryTest {

        @Autowired
    private GuestbookRepository guestbookRepository;


        //insert test
        @Test
        public void insertDummies(){
            IntStream.rangeClosed(1,300).forEach(i->{
                GuestBook guestbook = GuestBook.builder()
                        .title("제목  "+i)
                        .content("내용 "+i)
                        .writer("사용자"+(i%10))
                        .build();
                System.out.println(guestbookRepository.save(guestbook));

            });
        }

        //update test
        @Test
        public void updateTest() {

            Optional<GuestBook> result = guestbookRepository.findById(300L);

            if (result.isPresent()) {
                GuestBook guestBook =  result.get();
                guestBook.changeContent(" 컨텐트 수정 부분");
                guestBook.changeTitle(" 타이틀 수정 부분");

                guestbookRepository.save(guestBook);


            }
        }
        //querydsl test
        @Test
        public void testQuerydsl1(){

            Pageable pageable =  PageRequest.of(0, 10, Sort.by("gno").descending());
            System.out.println(pageable);

            QGuestBook qGuestBook = QGuestBook.guestBook;

            String keyword = "3";

            BooleanBuilder builder  =new BooleanBuilder();

            BooleanExpression booleanExpression = qGuestBook.title.contains(keyword);

            System.out.println("booleanExpression : " + booleanExpression);

            builder.and(booleanExpression);

            Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);

            result.stream().forEach(guestBook -> {
                System.out.println("최종 : "+guestBook);
            });



        }
}