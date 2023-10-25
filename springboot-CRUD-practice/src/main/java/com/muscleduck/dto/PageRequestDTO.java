package com.muscleduck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
//페이지 요청 처리 DTO(화면에서 전달되는 목록 관련 데이터에 대한 DTO)
public class PageRequestDTO {

    private int page;
    private int size;

    //나중에 검색 조건에 사용 할 변수
    private String type;
    private String keyword;


    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){

        return PageRequest.of(page -1, size, sort);

    }
}