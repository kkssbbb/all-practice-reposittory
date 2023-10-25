package com.kkssbbb.blogs.repository;

import com.kkssbbb.blogs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//db에인서트하기위해 만든 인터페이스
// JpaRepository 는 User테이블이 관리하는 레파지토리이고, 유저테이블의 프라이머리키는 인티저(숫자)이다.
//자동으로 Bean등록이가능해서 @Repository 생략가능하다.
public interface UserRepository extends JpaRepository<User,Integer> {
    //SELECT * FROM user WHERE username = 1?;
        Optional<User> findByUsername(String username);
}





//JPA Naming 쿼리
// SELECT * FROM user WHERE username = ? AND password =?
//User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT*FROM user WHERE username = ?1 AND password =?2 , nativeQuery=true")
//    User login(String username, String password);