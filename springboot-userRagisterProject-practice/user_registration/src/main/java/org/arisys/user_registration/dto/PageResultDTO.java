package org.arisys.user_registration.dto;


import lombok.Data;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Log4j2
public class PageResultDTO<DTO, ENTITY> {
//Page<Entity>의 엔티티 객체들을 DTO 객체로 변환해서 자료구조로 담는다.
    //화면 출력에 필요한 페이지 정보들을 구성.
    private List<DTO> dtolist;

    //총 페이지
    private int totalPage;
    //현재 페이지 번호
    private int currentPage;
    //목록 사이즈
    private int size;
    //시작 페이지 번호, 끝 페이지 번호
    private int start , end;
    //이전, 다음
    private boolean prev, next;
    //페이지 번호 목록
    private List<Integer> pageList;


    //기본 생성자.
    public PageResultDTO(Page<ENTITY> result , Function<ENTITY,DTO> fn) {

        dtolist = result.stream().map(fn).collect(Collectors.toList());
    /*result를 스트림으로 변환
    * map(fn) 호출하여 각 요소에 fn 함수 적용
    * Collectors.toList()하여서 스트림 요소를 리스트로 변환해서 dtoList 에 저장
    * */


        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
        //getPageable() 현재 페이지의 페이징 및 정렬 정보를 가진 Pageable객체 반환

    }

    private void makePageList(Pageable pageable) {
    log.info("makePageList () 호출 로그");
        this.currentPage = pageable.getPageNumber() +1;
        this.size = pageable.getPageSize();


        int tempEnd = (int) (Math.ceil(currentPage / 10.0) * 10);

        start = tempEnd - 9;

        prev = start >1;

        end = totalPage > tempEnd ? tempEnd : totalPage;
        log.info("end : " + end);
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
        //

    }


}
