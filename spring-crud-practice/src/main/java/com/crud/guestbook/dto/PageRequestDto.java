package com.crud.guestbook.dto;

//목록 페이지를 요청할 떄 사용하는 데이터를 재사용하기 쉽게 만드는 클래스
//페이지번호, 페이지 내 목록의 개수, 검색 조건같은 파라미터를  DTO 로 선언하고 나중에 재사용하는 용도의 클래스
//PageRequestDto = 목록관련 데이터에 대한 DTO
// 화면에서 전달되는 page,size 파라미터를 수집하는 역할 (컨트롤러에서 서비스에 전달)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDto {


    //현재 페이지 번호
    private int page;
    //목록 페이지 번호
    private int size;
    // 검색 타입
    private String type;
    // 검색 키워드
    private String keyword;




    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page - 1, size, sort);
    }

}
