package com.crud.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//엔티티에서 dto
//jpa는 페이징 처리 결과를 Page<Entity> 타입으로 반환한다
// 따라서 서비스계층에서 이를 처리하기위해 별도의 클래스를 만들어 처리하기 위해 만든 클래스이다.
//jpaRepository 가 페이징을해 반환한 Page<Entity> 타입의 객체를 DTO 객체로 변환해 자료구조에 담아 주어야한다.
//화면 출력에 필요한 페이지 정보들을 구성해 주어야한다.
//java.util.function.Function; 은 엔티티를 DTO 로 변환해 주는 기능이다.
@Data
public class PageResultDto<DTO, EN> {
    //DTO 리스트
    private List<DTO> dtoList;
    //총 페이지 번호
    private int totalPage;
    //현재 페이지 번호
    private int page;
    //목록 사이즈
    private int size;
    //시작페이지 번호, 끝페이지 번호
    private int start, end;
    //이전, 다음
    private boolean prev, next;
    //페이지 번호 목록
    private List<Integer> pageList;



    public PageResultDto(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1; //0부터 시작으로 1 추가
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        //끝 페이지 계산 ex) 현재 페이지가 13이면 10나누고 1.3을 Math.ceil()로 반올림 하면 2.0 *10하면 끝페이지는 20
        start = tempEnd - 9;
        //시작페이지 계산 20-9 11이 첫페이지
        prev = start > 1;
        // 시작페이지가 1 보다 크면 이전으로가는 링크 필요
        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage >tempEnd;
        //다음으로 가는 링크
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }
}
