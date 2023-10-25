package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BookForm {

    //상품 수정시 id 필요
    private Long id;
    //이름
    private String name;

    //가격
    private int price;

    //재고
    private int stockQuantity;

    //작가
    private String author;

    //isbn
    private String isbn;
}
