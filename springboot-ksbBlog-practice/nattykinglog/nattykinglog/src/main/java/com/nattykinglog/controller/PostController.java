package com.nattykinglog.controller;


import com.nattykinglog.request.PostCreate;
import lombok.extern.log4j.Log4j2;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Log4j2
public class PostController {

    // 데이터 받는 방법 1
    @PostMapping("/post")
    public String post(@RequestParam String title, @RequestParam String content) {
       log.info("title={},content={} ", title,  content);
        return "hello 승빈";
    }
    //방법 2
    @PostMapping("/post2")
    public String post2(@RequestParam String title, @RequestParam String content) {
        log.info("title={},content={} ", title,  content);
        return "hello 승빈";
    }

    //방법 3
    @PostMapping("/post3")
    public String post3(PostCreate params) {
        log.info("params={} ", params.toString());
        return "hello 승빈";
    }

    //방법 3 -> json 형식으로 받을때
    @PostMapping("/post4")
    public Map<String,String> post4(@RequestBody @Valid PostCreate params, BindingResult bindingResult) throws Exception {
        log.info("post4 호출 테스트");
        log.info("params={} ", params.toString());

        //아래 검증의 문제점
        /*
        * 1. 메번 메서드마다 값을 검증 해야한다.
        *  -> 개발자가 까먹을 수 있다.
        *  -> 검증 부분에서 버그가 발생할 여지가 높다.
        *  -> 지겹다.
        * 2. 응답값에 HashMap -> 응답 클래스를 따로 응답에맞는 형태의 만들어 주는게 좋다.
        * 3. 여러개의 에러처리 힘들다.
        * 4. 세 번이상의 반복적인 작업은 피해야한다.
        * */

        if (bindingResult.hasErrors()) {
            log.info("왜 에러 안찍히지?");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors(); //에러 몽땅 가져와서
            FieldError firstFieldError = fieldErrors.get(0);
            String  fieldName = firstFieldError.getField(); //title
            String errorMessage =  firstFieldError.getDefaultMessage(); //..에러 메세지

            Map<String, String> err = new HashMap<>();
            err.put(fieldName, errorMessage);
            return err;

        }

        return Map.of();


        //데이터 검증
        //@Valid 어노테이션으로 @NotBlank 어노테이션 걸려있는 필드를 검증한다.

        //필드를 한 가지씩 검증 은 빡세다(노가다)
        //2. 개발팁 -> 무언가 3번이상 반복작업을 할때 내가 뭔가 잘못하고 있는건 아닐지 의심한다.
        //3. 필드 누락가능성
        //4. 생각보다 검증해야할 게 많다.(꼼꼼하지 않을 수 있다.)
        //5. 뭔가 개발자 스럽지 않다. -> 간지 x
        // solution : @NotBlank 라는 데이터 검증기능이 이미 스프링에 있다.

        /*
        String title = params.getTitle();
        if (title == null || title.equals("")) {
            throw new Exception("타이틀값이 없어용");
        }

        String content =  params.getContent();
        if (content == null || content.equals("")) {
            throw new Exception("컨탠트값이 없어용");
        }
*/
    }

    //@ContreollerAdvice를 이용한 검증
    @PostMapping("/post5")
    public Map<String, String> testes(@RequestBody @Valid PostCreate params) {

        return Map.of();
    }
}
