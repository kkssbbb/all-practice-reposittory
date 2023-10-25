package com.muscleduck.repository;

import com.muscleduck.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 데이터 더미 추가")
    void testUserData(){

        for (int i = 0; i < 100; i++) {
            UserEntity user = UserEntity.builder()
                    .email("test"+i+"@email.com")
                    .password("1234")
                    .nickname("홍길동" + i)
                    .build();

            userRepository.save(user);
        }

    }

}