package com.crud.guestbook.controller;


import com.crud.guestbook.dto.GuestBookDto;
import com.crud.guestbook.dto.PageRequestDto;


import com.crud.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor //자동주입을위한 어노테이션
@Log4j2
@RequestMapping("/guestbook")
public class GuestBookController {

    private final GuestBookService guestBookService;

    @GetMapping("/")
    public String index(){
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")//글 목록
    public void list(PageRequestDto pageRequestDto, Model model){

        log.info("list............." + pageRequestDto);
model.addAttribute("result", guestBookService.getList(pageRequestDto));

    }

    //글등록 화면 요청
    @GetMapping("/register")
    public void register(){
        log.info("register get 요청");

    }
    //글등록 수정폼 전전달
    @PostMapping("/register")
    public String registerPost(GuestBookDto guestBookDto, RedirectAttributes redirectAttributes){

        log.info("register Post 요청");

        //새로 추가된 엔티티의 번호
        Long gno = guestBookService.register(guestBookDto);

        redirectAttributes.addFlashAttribute("msg", gno);
        //addFlashAttribute() = 단 한번만 데이터를 전달하는 용도로 쓰인다.
        return "redirect:/guestbook/list";

    }

    //읽기 수정
    @GetMapping({"/read","/modify"})
    public void read(Long gno, @ModelAttribute("requestDTO") PageRequestDto requestDTO, Model model ){

        log.info("gno: " + gno);

        GuestBookDto dto = guestBookService.read(gno);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/modify")
    public String modify(GuestBookDto guestBookDto,
                         @ModelAttribute("requestDTO") PageRequestDto pageRequestDto,
                         RedirectAttributes redirectAttributes){
        
        
        log.info("=============post modify==========");
        log.info("DTO : "+guestBookDto);

        guestBookService.modify(guestBookDto);

        redirectAttributes.addAttribute("page", pageRequestDto.getPage());
        redirectAttributes.addAttribute("gno" , guestBookDto.getGno());

        return "redirect:/guestbook/read";
    }

    //삭제
    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes){

        log.info("gno: " +gno);

        guestBookService.remove(gno);

        redirectAttributes.addFlashAttribute("msg",gno);

        return "redirect:/guestbook/list";
    }


///////////////
    @GetMapping("/test")
    public String test(){
        return "/guestbook/test";
    }
}
