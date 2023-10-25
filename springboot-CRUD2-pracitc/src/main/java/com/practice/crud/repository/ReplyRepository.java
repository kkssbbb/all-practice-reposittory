package com.practice.crud.repository;

import com.practice.crud.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying
    @Query("DELETE FROM Reply r where r.board.bno =:bno")
    void delteByBno(Long bno);

}
