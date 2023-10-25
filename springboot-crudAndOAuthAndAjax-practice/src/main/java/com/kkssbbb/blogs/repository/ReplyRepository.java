package com.kkssbbb.blogs.repository;


import com.kkssbbb.blogs.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



public interface ReplyRepository extends JpaRepository<Reply, Integer>{


    @Modifying
    @Query(value="INSERT INTO reply(userId, boardId, content) VALUES(?1, ?2, ?3)" , nativeQuery = true)
    int mSave(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해줌.\
    //
}