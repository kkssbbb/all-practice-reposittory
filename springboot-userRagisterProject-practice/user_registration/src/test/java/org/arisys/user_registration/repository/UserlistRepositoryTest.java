package org.arisys.user_registration.repository;

import org.arisys.user_registration.entity.UsersEntity;
import org.checkerframework.common.value.qual.DoesNotMatchRegex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;




@SpringBootTest
public class UserlistRepositoryTest {

    @Autowired
    private UserlistRepository userlistRepository;

    @Test
    @DisplayName("유저 디비 인서트 테스트")
    public void insertTest(){
        IntStream.rangeClosed(1,200).forEach(i ->{
            UsersEntity usersEntity = UsersEntity.builder()
                    .name("김승빈"+ i)
                    .age(i)
                    .gender( "남자")
                    .job("개발자")
                    .build();
            System.out.println(userlistRepository.save(usersEntity));

        });
    }

    @Test
    @DisplayName("파인드 올 테스트")
    public void findtest() {


        System.out.println(   userlistRepository.findAll(Sort.by(Sort.Direction.DESC,"userId")));
    }

    @Test
    @DisplayName("수정시간 적용 테스트")
    public void updateTest(){
        Optional<UsersEntity> findUser =  userlistRepository.findById(1L);

        UsersEntity usersEntity =   findUser.get();

        usersEntity.changeGender("여자");

        userlistRepository.save(usersEntity);

    }
}