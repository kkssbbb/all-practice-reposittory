package org.springex.gestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO <DTO,EN>{

    public List<DTO> dtoList;

    //총 페이지 번호
    private int totalPageNum;
    //현재 페이지 번호
    private int currentPageNum;
    //목록 사이즈
    private int listSize;
    //시작 페이지 번호, 끝페이지 번호
    private int startPageNum, endPageNum;
    //이전, 다음
    private boolean prev, next;
    //페이지 번호 목록
    private List<Integer>   pageList;

    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){
        // 생성자: Page<EN> 타입의 result와 EN을 DTO로 변환하는 Function<EN,DTO> fn을 받음
                dtoList = result.stream().map(fn).collect(Collectors.toList());
        //collect(Collectors.toList()); = 스트림의 요소들을 List객체로 변환
        // result를 스트림으로 변환한 후, 각 요소에 fn 함수를 적용하여 DTO로 변환한 뒤 dtoList에 저장
        // => EN 타입의 객체들을 DTO로 변환한 후, dtoList에 저장하는 작업 수행
        totalPageNum = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.currentPageNum = pageable.getPageNumber() +1;
        this.listSize = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(currentPageNum/10.0))*10;

        startPageNum = tempEnd -9;

        prev = startPageNum > 1;

        endPageNum = totalPageNum > tempEnd ? tempEnd: totalPageNum;

        next = totalPageNum > tempEnd;

        pageList = IntStream.rangeClosed(startPageNum, endPageNum).boxed().collect(Collectors.toList());
    }

    }





