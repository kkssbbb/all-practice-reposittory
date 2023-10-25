package com.example.ex2.repository;

import com.example.ex2.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo,Long> {

}
