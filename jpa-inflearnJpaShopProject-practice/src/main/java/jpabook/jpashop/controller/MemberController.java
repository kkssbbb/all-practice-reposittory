package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MemberController {


    private  final MemberService memberService;


    //회원등록 페이지 요청
    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
    //회원등록 페이지에서 회원 생성
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";

    }

    //회원 목록 컨트롤러
    @GetMapping("members")
    public String create(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
