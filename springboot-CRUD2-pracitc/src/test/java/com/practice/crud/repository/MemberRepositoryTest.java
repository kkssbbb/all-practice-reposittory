package com.practice.crud.repository;

import com.practice.crud.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버더미 생성")
    void createMember(){

        for (int i = 0; i < 100; i++) {


            Member member = Member.builder()
                    .email("test"+i+"@naver.com")
                    .name("홍길동"+i)
                    .password("password"+i)
                    .build();

            memberRepository.save(member);
        }


    }

}