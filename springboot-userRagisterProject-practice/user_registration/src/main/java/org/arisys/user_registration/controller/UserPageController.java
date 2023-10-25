package org.arisys.user_registration.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.arisys.user_registration.dto.PageRequestDTO;
import org.arisys.user_registration.dto.UserDTO;
import org.arisys.user_registration.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/page")
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;// 자동 의존성주입을 위해 final 선언

    @GetMapping("/home")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
    //모델 객체 Controller에서 생성된 데이터를  View로 전달할 때 사용하는 객체이다.
        log.info("list()  컨틀롤러 호출 로그");
        log.info("getlist 리턴값 : "+userService.getList(pageRequestDTO));
        model.addAttribute("result", userService.getList(pageRequestDTO));
    }

    //사용자 등록
   // @PostMapping("/register")
    public String register(UserDTO userDTO, RedirectAttributes redirectAttributes) {

        log.info("userDTO : " +userDTO);

        log.info("컨트롤러 register() 호출 로그");

        Long userId = userService.register(userDTO);

        redirectAttributes.addAttribute("msg", userId);

        return "redirect:/page/home";

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        log.info("userDTO 확인용 로그 : " + userDTO);

        try {
            Long userId = userService.register(userDTO);
            log.info("userDTO 확인용 로그 : " + userDTO);
            return ResponseEntity.ok(userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
        }
    }
    //사용자 삭제 1
    //@PostMapping("/remove")
    public String remove(Long userId, RedirectAttributes redirectAttributes) {
        log.info("컨트롤러 remove() 호출로그");
        log.info("유저아이디보기 : " +userId );

        userService.remove(userId);

        redirectAttributes.addAttribute("msg", userId);

        return "redirect:/page/home";
    }

    //사용자삭제 2
    @PostMapping("/remove")
    public ResponseEntity<String> removeUser(@RequestParam("userId") Long userId) {
        log.info("컨트롤러 remove() 호출로그");
        log.info("유저아이디보기 : " +userId );
        // 사용자 삭제 로직 수행
        userService.remove(userId);

        // 리다이렉트 URL 생성
        String redirectUrl = "/page/home";

        // 리다이렉트 시 메시지 전달을 위한 RedirectAttributes 사용하지 않음

        // 응답 생성
        return ResponseEntity.ok(redirectUrl);
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("userId") Long userId, @RequestBody UserDTO userDTO) {
        log.info("업데이트 컨트롤러 호출로그");
        log.info("userID : " + userId);
        log.info("userDTO : " + userDTO);
        userService.updateUser(userId, userDTO);
        return "redirect:/page/home";
    }


    @GetMapping("/user-list-douwnload")
    public void dbToExcel(PageRequestDTO pageRequestDTO, Model model) {
        //모델 객체 Controller에서 생성된 데이터를  View로 전달할 때 사용하는 객체이다.
        log.info("dbToExcel() 호출 로그");
        userService.dbToExcel();
        log.info("list()  컨틀롤러 호출 로그");
        log.info("getlist 리턴값 : "+userService.getList(pageRequestDTO));
        model.addAttribute("result", userService.getList(pageRequestDTO));
    }

    }


