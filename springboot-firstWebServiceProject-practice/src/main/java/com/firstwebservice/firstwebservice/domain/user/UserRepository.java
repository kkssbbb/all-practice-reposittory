package com.firstwebservice.firstwebservice.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//User 의 CRUD 를 책임지는 클래스
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email); //findByEmail = 소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하는 메서드

}
