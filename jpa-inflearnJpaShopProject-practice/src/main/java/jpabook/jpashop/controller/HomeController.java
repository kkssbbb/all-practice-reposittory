package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home() {
//        log.trace("트레이스 로깅");
//        log.warn("경고 로깅");
//        log.info("정보 로깅");
//        log.debug("디버깅용 로그");
//        log.error("에러로그");
//        log.info("HomeController, 테스트 보이나");
        return "home";
    }
}
