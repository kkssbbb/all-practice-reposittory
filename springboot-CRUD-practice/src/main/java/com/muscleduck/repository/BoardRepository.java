package com.muscleduck.repository;

import com.muscleduck.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    /*목록화면 JPQL 작성 시작*/


    //목록화면에서 게시글 정보 가져오기
    @Query("SELECT b, w from BoardEntity b left join  b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    //특정게시물과 해당 게시물에 속한 댓글들 조회
    @Query("SELECT b,r from BoardEntity b left join ReplyEntity r on r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);


    //목록화면에 필요한 데이터
    //그룹 함수를 이용해서 하나의 게시글당 하나의 라인이 됳 수 있도록 처리 (Pageable을 파라미터로 받아 페이징 처리)
    //count() = JPQL 그룹 함수
    @Query(value = "SELECT b,w, count(r)" +
            " FROM BoardEntity b" +
            " LEFT JOIN b.writer w" +
            " LEFT JOIN ReplyEntity r on r.board = b" +
            " GROUP BY b",
            countQuery = "SELECT count (b) FROM  BoardEntity  b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
    /*목록화면 JPQL 작성 끝*/

    /*조회화면 JPQL 작성 시작*/
    @Query(" SELECT b,w, count(r)" +
            " FROM BoardEntity b" +
            " LEFT JOIN b.writer w" +
            " LEFT JOIN ReplyEntity r"+
            " on r.board = b"+
            " WHERE b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);
    /*조회화면 JPQL 작성 끝*/

}
