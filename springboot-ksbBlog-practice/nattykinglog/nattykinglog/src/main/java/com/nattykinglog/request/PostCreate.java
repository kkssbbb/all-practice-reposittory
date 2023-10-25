package com.nattykinglog.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

//DTO
@ToString
@Getter
@Setter
public class PostCreate {

    @NotBlank(message = "타이틀 값이 없어용")
    private String title;

    @NotBlank(message = "컨텐트값이 없어용")
    private String content;

    //데이터 검증 부분


    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }


    @Override
    public String toString() {
        return "PostCreate{" +
                "title : " + title +
                "content : " + content +
                "}";
    }


}
