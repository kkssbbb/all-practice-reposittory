package com.practice.crud.entity;

import lombok.CustomLog;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
//@MappedSuperclass 어노테이션이 적용된 클래스는 테이블로 생성되지 않는다.
//실제 테이블은   BaseEntity 클래스를 상속한 엔티티의 클래스로 테이블이 생성된다.
@EntityListeners(value = {AuditingEntityListener.class})
// @EntityListeners = Persistence Context 의 변화를 감지하는 리스너가 있다.
// JPA내부에서 엔티티 객체가 생성/변견 되는 것을 감지하는 역할을 "AuditingEntityListener.class" 가 한다.
// 이를 통해 regDate, modDate 에 적절한 값이 지정된다.
@Getter
abstract class BaseEntity {

    @CreatedDate
    // @CreatedDate = JPA 에서 엔티티의 생성 시간을 처리한다.
    @Column(name = "regdate" , updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    // @LastModifiedDate = 최종 수정 시간을 자동으로 처리한다.
    //속석으로 insertable, updatable 등이 있다.
    //updatable = false = 객체를 데이터 베이스에 반영할 때 regdate 칼럼값은 변경되지 않는다.
    @Column(name = "moddate")
    private LocalDateTime modDate;


}
