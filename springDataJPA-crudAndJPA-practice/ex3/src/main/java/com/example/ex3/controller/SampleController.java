package com.example.ex3.controller;

import com.example.ex3.dto.SampleDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2

public class SampleController {


    @GetMapping("/ex1")
    public void ex() {
        log.info("ex 로그");
    }


    @GetMapping("/ex2")
    public void exModel(Model model) {
        log.info("ex2 로그");
        List<SampleDto> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
            SampleDto sampleDto = SampleDto.builder()
                    .sno(i)
                    .first("첫번쨰" + i)
                    .last("마지막" + i)
                    .time(LocalDateTime.now())
                    .build();

            return sampleDto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);

    }

    @GetMapping({"/exInline"})
    public String exInline(RedirectAttributes redirectAttributes) {

        log.info("인라인");

        SampleDto dto = SampleDto.builder()
                .sno(100L)
                .first("첫번쨰..100")
                .last("마지막..100")
                .time(LocalDateTime.now())
                .build();
        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/sample/ex3";

    }

    @GetMapping("ex3")
    public void ex3() {
        log.info("ex3호출");

    }
    @GetMapping({"/exlayout1", "/exlayout2","/exSidebar"})
    public void exLayout(){
        log.info("이엑스레이아웃 호출이요");
    }

}
