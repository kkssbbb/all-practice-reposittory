package org.arisys.user_registration.service;

import org.apache.catalina.User;
import org.apache.coyote.http11.filters.IdentityOutputFilter;
import org.arisys.user_registration.dto.PageRequestDTO;
import org.arisys.user_registration.dto.PageResultDTO;
import org.arisys.user_registration.dto.UserDTO;
import org.arisys.user_registration.entity.UsersEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserServiceImpTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("dto to entity Test")
    public void dtotoentitytest() {

        UserDTO userDTO = UserDTO.builder()
                .name("한주먹")
                .age(26)
                .job("바디빌더")
                .gender("남자")
                .build();
        System.out.println(userService.register(userDTO));
    }

    @Test
    @DisplayName("목록처리 테스트")
    public void testGetList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<UserDTO, UsersEntity> resultDTO = userService.getList(pageRequestDTO);

        for (UserDTO userDTO : resultDTO.getDtolist()) {
            System.out.println(userDTO);
        }
    }

    @Test
    @DisplayName("목록처리 테스트")
    public void testLIst() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<UserDTO, UsersEntity> resultDTO = userService.getList(pageRequestDTO);

        System.out.println(resultDTO.isPrev());
        System.out.println(resultDTO.isNext());
        System.out.println(resultDTO.getTotalPage());

        System.out.println("=======================================================");
        for (UserDTO userDTO : resultDTO.getDtolist()) {
            System.out.println(userDTO);
        }

        System.out.println("=========================================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));

    }

    @Test
    @DisplayName("Querydsl 검색 처리 테스트")
    public void testQuerydsl() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("ngj")
                .keyword("개발자")
                .build();

        PageResultDTO<UserDTO, UsersEntity> pageResultDTO = userService.getList(pageRequestDTO);

        System.out.println("isPrev : " + pageResultDTO.isPrev());
        System.out.println("isNext : " + pageResultDTO.isNext());
        System.out.println("getTotalPage : " + pageResultDTO.getTotalPage());
        System.out.println("========================================================");
        for (UserDTO userDTO : pageResultDTO.getDtolist()) {
            System.out.println(userDTO);
        }
        System.out.println("========================================================");
        pageResultDTO.getPageList().forEach(i -> System.out.println(i));


    }

    @Test
    @DisplayName("디비 투 엑셀 테스트")
    public void testDbtoExcel() {
        userService.dbToExcel();
    }
}
