package com.firstwebservice.firstwebservice.web.dto;

import com.firstwebservice.firstwebservice.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬북_기능_테스트(){
        /*given,when,then 문법
        * given이 주어짐 when주어진걸 실행 했을때  then이러한 결과가 나와야한다.
        * */

        //given
        String name = "test";
        int amount = 1000;
        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);


    }

}
