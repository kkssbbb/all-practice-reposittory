package com.kkssbbb.blogs.test;

import com.kkssbbb.blogs.model.RoleType;
import com.kkssbbb.blogs.model.User;
import com.kkssbbb.blogs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController //데이터 응답
public class DummyControllerTest {

    @Autowired //의존성주입(DI)
    private UserRepository userRepository;

    //save 함수는 id를 전달하지 않으면 insert를 하고 , id를 전달하면 해당 id에 대한 데이터가있으면 update를 하며,
    //id에 대한 데이터가 없으면 insert를 한다.

   @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
       try {
           userRepository.deleteById(id);
       } catch (Exception e) {
       return "삭제에 실패하였습니다. 아이디를 확인해 주세요";
       }
           return "삭제되었습니다." + id;
       }

    @Transactional //더티체킹 ,함수 종료시 자동commit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("아이디 : "+id);
        System.out.println("패스워드 : "+requestUser.getPassword());
        System.out.println("메일 : "+requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패 하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);
        return null;
    }

    //모든유저 조회
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한페이지단 2건의 데이터를 리턴받는 함수
    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser=userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return pagingUser;
    }


    //{id} 주소로 파라미터를 전달 받을 수 있다.
    //ex) http://localhost8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        //user/3 까지밖게없는데 user/4 를 찾으면 데이터베이스에서 못찾아서 user가 null값이된다.
        //그럼 return null 이 리턴이 되어서 프로그램에 문제아 있을 수 있다.
        //그래서 Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return한다.

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당유저는 없습니다." + id);
            }
        });
        return user;
    }   //요청: 웹브라우저 , user객체 : 자바오브젝트이고 @RestController 는 데이터를 전송한다.
        //그래서 웹브라우저가 이해할수있는 데이터인 json으로 변환해중야하고
        //자바 오브젝트를 리턴할때 스프링부트의 MessageConverter가 자동으로 Jackson 라이브러리를 호출해서
        //user 오브젝트를 json으로 변환해서 브라우저에 던져준다.



    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("username : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        System.out.println("email : "+user.getPassword());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
