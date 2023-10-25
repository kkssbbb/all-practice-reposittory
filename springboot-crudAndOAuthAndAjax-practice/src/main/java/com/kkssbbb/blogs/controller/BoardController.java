package com.kkssbbb.blogs.controller;

import com.kkssbbb.blogs.config.auth.PrincipalDetail;
import com.kkssbbb.blogs.service.BoardService;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @NotFound(action = NotFoundAction.IGNORE)
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("boards",boardService.글목록(pageable));
        //   /WEB-INF/views/user.bustache
        //System.out.println("로그인 사용자 아이디"+principal.getUsername());
        return "index"; //viewResolver 적용 앞 뒤로 prefix,suffix를 붙여준다.
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("board",boardService.글상세보기(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }

    //user권한 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
