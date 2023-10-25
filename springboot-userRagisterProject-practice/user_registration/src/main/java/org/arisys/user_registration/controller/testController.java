package org.arisys.user_registration.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
public class testController {

 //   @GetMapping("/")
    public String test(){
    log.info("test  호출 로그 ");
        return "page/home";
    }
}
