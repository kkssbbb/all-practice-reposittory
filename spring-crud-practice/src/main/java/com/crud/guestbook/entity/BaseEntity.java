package com.crud.guestbook.entity;
/*엔티티 객체의 등록시간과 최종수정시간을 담당하는 추상클래스*/


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // = 해당 어노테이션 적용시 적용된 클래스는 테이블로 생성되지 않음 실제테이블은 이 클래스를 상속한 엔티티 클래스가 테이블로 생성됨
@EntityListeners(value = {AuditingEntityListener.class}) //@EntityListeners = 엔티티변화 감지역할 , AuditingEntityListener.class = JPA 내부 엔티티 객체 생성변경 감지 역할
@Getter
abstract class BaseEntity {

    @CreatedDate  //JPA 엔티티 생성시간 처리
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate //엔티티 최종 수정시간 처리 용도
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
