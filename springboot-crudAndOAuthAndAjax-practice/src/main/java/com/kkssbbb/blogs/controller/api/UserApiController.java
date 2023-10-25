package com.kkssbbb.blogs.controller.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kkssbbb.blogs.config.auth.PrincipalDetail;
import com.kkssbbb.blogs.dto.ResponseDto;
import com.kkssbbb.blogs.model.RoleType;
import com.kkssbbb.blogs.model.User;
import com.kkssbbb.blogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class UserApiController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private AuthenticationManager authenticationManager;



    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { //username, password , email
        System.out.println("User ApiController : save호출됨 ");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @PutMapping("/user")
        public ResponseDto<Integer> update(@RequestBody User user){
            userService.회원수정(user);
            //여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경 됐음
            //하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.

        //세션등록
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }

    }


//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("UserApiController : login호출됨");
//        User principal = userService.로그인(user); // principal 접근주체
//
//        if(principal!=null){
//            session.setAttribute("principal",userService.로그인(user));
//        }
//        System.out.println("principal :"+ principal );
//
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//    }

