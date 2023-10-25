package com.kkssbbb.blogs.controller.api;

import com.kkssbbb.blogs.config.auth.PrincipalDetail;
import com.kkssbbb.blogs.dto.ReplySaveRequestDto;
import com.kkssbbb.blogs.dto.ResponseDto;
import com.kkssbbb.blogs.model.Board;
import com.kkssbbb.blogs.model.Reply;
import com.kkssbbb.blogs.model.User;
import com.kkssbbb.blogs.service.BoardService;
import com.kkssbbb.blogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {


    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        System.out.println("삭제하기"+id);
    boardService.글삭제하기(id);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")  //deleteById 와 주소가 같지만 맵핑 메서드가 다르기때문에 괜찮다.
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
    boardService.글수정하기(id,board);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다. ->데이터가 많지않으면 상관없지만 데이터가 많으면 모델로 데이터를 받는건 좋은 방법이 아니다.
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {


        boardService.댓글쓰기(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제(replyId);
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

