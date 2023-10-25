package com.firstwebservice.firstwebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//Post 클래스로 DataBase 를 접근하게 해줄 JPARepository 생성
public interface PostsRepository extends JpaRepository<Posts,Long> {  //JpaRepository<Entity 클래스,PK 타입> 를 상속하면 기본적인 CRUD메서드 자동생성된다.

    @Query(value = "SELECT p FROM Posts p order by  p.id DESC") //SpringDataJpa에서 제공하지 않는 메소드를 쿼리로 작성 가능하다.
    List<Posts> findAllDesc();
}

//TMI 규모가있는 프로젝트는 데이터 조희를 FK의 조인, 복잡한 조등으로 Entity클래스만으로 처리하기 어려워
//조회용 프레임워크를 추가로 사용한다. 대표적으로 querydsl,jooq,MyBatis 등이 있다.
//조회는 위 3가지 프레임워크 중 하나를 통해 조회하고, 등록/수정/삭제 등은 SpringDataJpa 를 한다.
//저자는 아래와 같은 장점으로 Querydsl 을 추천
//1.타입안정성 보장 2.국내 많은 회사 사용 중 3.래퍼런스가 많음
