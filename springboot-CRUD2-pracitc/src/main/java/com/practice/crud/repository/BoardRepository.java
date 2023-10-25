package com.practice.crud.repository;

import com.practice.crud.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    /*목록 화면 처리
    * 게시글
    * 회원이름
    * 해당 게시글 댓글 수
    * */

    //한개의 로우(object) 내에 object[]로 나옴
    //게시글과 작성자 조인
    @Query("select b, w from  Board b left join b.writer w where b .bno =:bno")
    Object getBoardWriter(@Param("bno") Long bno);


    // 연관관계가 없는경우 join 은 "on"을 이용해서 조인을 할 수 있다.
    // 특정 게시물과 해당 게시물에 속한 댓글을 조회  = 게시글과 댓글 조인
    @Query("select b,r from Board b left join Reply r on r.board = b where  b.bno = :bno")
    List<Object[]> getBoardWithRply(@Param("bno") Long bno);


    //그룹 함수를 이용해서 하나의 게시글 당 하나의 라인이 될 수 있도록 처리
    //count() = JPQL 그룹 함수
    @Query(value ="SELECT b, w, count(r) " +
            " FROM Board b " +
            " LEFT JOIN b.writer w " +
            " LEFT JOIN Reply r ON r.board = b " +
            " GROUP BY b",
            countQuery ="SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable); //목록 화면애 필요헌 데이터



    /*조회 화면 처리
    * 게시글
    * 댓글 개수
    * 회원*/


    @Query("SELECT b, w, count(r) " +
            " FROM Board b LEFT JOIN b.writer w " +
            " LEFT OUTER JOIN Reply r ON r.board = b" +
            " WHERE b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);







}
