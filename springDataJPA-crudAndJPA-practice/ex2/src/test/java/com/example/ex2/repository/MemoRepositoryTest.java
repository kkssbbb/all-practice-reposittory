package com.example.ex2.repository;

import com.example.ex2.entity.Memo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {

        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {

        for (int i = 0; i <= 100; i++) {
            Memo memo = Memo.builder().memoText("test..." + i).build();
            memoRepository.save(memo);

        }
    }

    @Test
    @DisplayName("수정테스트")
    public void updateTest() {
        Memo memo = Memo.builder().mId(1L).memoText("1번 수정테스트").build();
        memoRepository.save(memo);

    }

    @Test
    @DisplayName("페이징 처리 테스트")
    public void pagingTest() {
        //Spring Data JPA를 이용할때 페이징 처리는 0부터시작
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("result = " + result);

        System.out.println(result.getTotalPages());
        System.out.println(result.getTotalElements());
        System.out.println(result.getSize());

        System.out.println("===============================================");

        for (Memo memo : result.getContent()) {

            System.out.println(memo);
        }
    }

    @Test
    @DisplayName("페이징 처리 테스트+정렬 조건 추가")
    public void testSort() {

        Sort sort = Sort.by("mId").ascending();
        Sort sort1 = Sort.by("memoText").descending();
        Sort sortAll = sort.and(sort1);



        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);
        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }
}