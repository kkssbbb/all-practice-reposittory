package com.crud.guestbook.service;

import com.crud.guestbook.dto.GuestBookDto;
import com.crud.guestbook.dto.PageRequestDto;
import com.crud.guestbook.dto.PageResultDto;
import com.crud.guestbook.entity.GuestBook;
import com.crud.guestbook.entity.QGuestBook;
import com.crud.guestbook.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor // 의존성자동 주입
public class GuestBookServiceImp  implements GuestBookService{

    private final GuestBookRepository repository;


    @Override //글 읽기
    public GuestBookDto read(Long gno) {
        Optional<GuestBook> result = repository.findById(gno);

        //결과가 있으면 DTO반환 없으면 null 반환
        return result.isPresent()? entityToDto(result.get()): null;
    }


    @Override //글 등록
    public Long register(GuestBookDto dto) {
        log.info("=============dto=============");
        log.info(String.valueOf(dto));

        GuestBook entity = dtoEntity(dto);

        log.info(String.valueOf(entity));

        repository.save(entity);

        return entity.getGno();
    }

    @Override //글 삭제
    public void remove(Long gno) {
        repository.deleteById(gno);
    }

    @Override//글 수정
    public void modify(GuestBookDto guestBookDto) {
        //업데이트 항목 제목, 수정
        Optional<GuestBook> result = repository.findById(guestBookDto.getGno());

        if(result.isPresent()) {

            GuestBook entitiy = result.get();

            entitiy.changeTitle(guestBookDto.getTitle());
            entitiy.changeContent(guestBookDto.getContent());

            repository.save(entitiy);
        }
    }
@Override
    public BooleanBuilder getSearch(PageRequestDto requestDTO){  //Querydsl 처리

        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QGuestBook qGuestbook = QGuestBook.guestBook;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qGuestbook.gno.gt(0L); // gno > 0 조건만 생성

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0){ //검색 조건이 없는 경우
            return booleanBuilder;
        }


        //검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }


    @Override
    public PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDto); // 검색 조건 처리
        Page<GuestBook> result =repository.findAll(booleanBuilder, pageable); //Querydsl 사용

       // Page<GuestBook> result = repository.findAll(pageable);

        Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));

        return new PageResultDto<>(result, fn);
    }


}
