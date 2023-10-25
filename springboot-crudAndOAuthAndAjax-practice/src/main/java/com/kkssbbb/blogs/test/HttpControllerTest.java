package com.kkssbbb.blogs.test;


import org.springframework.web.bind.annotation.*;
//사용자요청->응답(Html)은 @Controller 를 사용한다.

@RestController //사용자가 요청이있으면 응답으로 DATA를 응답해준다.
public class HttpControllerTest {

    // localhost:8000/blog/http/get
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get요청 : "+ m.getId()+" userName : "+m.getUserName()+m.getPassword()
                +m.getEmail();
    }
// 웹페이지 요청은 get만 가능하다.
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){ //MessageConverter(스프링부트)
        return "post요청 : "+ m.getId()+" ,"+m.getUserName()+" ,"+m.getPassword()
                +" ,"+m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(){
        return "put요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete요청";
    }

}
