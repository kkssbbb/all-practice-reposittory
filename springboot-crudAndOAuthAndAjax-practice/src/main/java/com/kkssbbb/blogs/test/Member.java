package com.kkssbbb.blogs.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//게터세터  셍성
@AllArgsConstructor // 모든곳에서 사용가능한 생성자 생성
@NoArgsConstructor //빈 생성자
public class Member {
    private int id;
    private String userName;
    private String password;
    private String email;

}