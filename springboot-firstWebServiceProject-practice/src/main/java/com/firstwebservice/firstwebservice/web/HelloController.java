package com.firstwebservice.firstwebservice.web;

import com.firstwebservice.firstwebservice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를  JSON을 반환하는 컨트롤러로 만들어 준다.
public class HelloController {

    @GetMapping("/hello") //HTTP Method인 GET의 요청을 받을 수 있는 API를 만들어준다.
    public String hello(){
        return "hello";

}
    @GetMapping("/hello/dto")
    //@RequestParam("name") = 외부에서 nmae 이란 이름으로 넘긴 파라미터를 String name에 저장한다.
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
    }


