package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter

public class MemberForm {

    @NotEmpty(message = "회원 이름을 기입해 주세요")
    //스프링부트 2.3부터는 implementation 'org.springframework.boot:spring-boot-starter-validation' 의존성을 추가해야 사용가능하다.
    private String name;

    @NotEmpty(message = "도시명을 기입해 주세요")
    private String city;

    @NotEmpty(message = "거리명을 기입해 주세요")
    private String street;

    @NotEmpty(message = "우편번호를 기입해 주세요")
    private String zipcode;


}
