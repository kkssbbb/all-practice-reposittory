package com.kkssbbb.blogs.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

      @GetMapping("/index")
    public String temphome(){
          return "Index";
      }

}
