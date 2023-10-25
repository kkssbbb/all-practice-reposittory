package org.springex.gestbook.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@Log4j2
public class testController {

    @GetMapping("/t")
    public String test(Model model) {
        model.addAttribute("text", "test승빈");
log.info("test.html 호툴 로그");
        return "test.html";
    }

}
