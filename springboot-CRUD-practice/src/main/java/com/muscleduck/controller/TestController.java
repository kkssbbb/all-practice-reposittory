package com.muscleduck.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
@RequestMapping
public class TestController {

    @GetMapping("/")
    public String main(){
    log.info("호출루루룰루ㅜ룰");
        return "page/mainpage.html";
    }
}
