package org.springex.gestbook.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springex.gestbook.dto.PageRequestDTO;
import org.springex.gestbook.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor// 자동 주입
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list() 호출 로그");

        model.addAttribute("result", guestbookService.getList(pageRequestDTO));

        log.info( "내가보싶은 로그"+ model.addAttribute("result", guestbookService.getList(pageRequestDTO)));
    }





}
