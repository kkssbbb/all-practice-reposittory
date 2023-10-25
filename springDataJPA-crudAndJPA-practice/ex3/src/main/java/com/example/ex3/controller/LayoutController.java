package com.example.ex3.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/layout")
public class LayoutController {

    @GetMapping("/callTest")
    public void callTest(){
        log.info("콜 테스트");
    }



}
