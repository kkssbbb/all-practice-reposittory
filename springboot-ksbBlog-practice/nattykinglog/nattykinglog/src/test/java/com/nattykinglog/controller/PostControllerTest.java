package com.nattykinglog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("출력 테스트")
    void test() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/post"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello 승빈"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @DisplayName("post 요청 테스트")
    void postTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/post3")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "글 제목입니다.")
                        .param("content", "글 내용입니다.")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello 승빈"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("post 요청 테스트 json 폼오르 데이터 전송")
    void postTest2() throws Exception {
   ///testtest 잔디누락 테스트
        mockMvc.perform(MockMvcRequestBuilders.post("/post4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\" : null , \"content\" : \"내용입니다 하하하\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.title").value("타이틀 값이 없어용"))
                .andDo(MockMvcResultHandlers.print());
    }

    //데이터 검증 테스트 부분
    //sd
    @Test
    @DisplayName("post 요청 테스트 json 폼오르 데이터 전송")
    void postTest3() throws Exception {
        ///testtest 잔디누락 테스트
        mockMvc.perform(MockMvcRequestBuilders.post("/post5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\" : null , \"content\" : \"내용입니다 하하하\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
           //     .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.title").value("타이틀 값이 없어용"))
                .andDo(MockMvcResultHandlers.print());
    }

}