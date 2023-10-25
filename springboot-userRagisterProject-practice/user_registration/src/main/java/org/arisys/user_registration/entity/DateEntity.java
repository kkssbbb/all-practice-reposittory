package org.arisys.user_registration.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //테이블 생성 x
@EntityListeners(value = {AuditingEntityListener.class}) //엔티티 변화 감지  //메인클래스에 따로 활성화 어노테이션 추가해야 활성화 됌
//AuditingEntityListener.class = JPA 내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 리스터
@Getter
abstract class DateEntity {

    @CreatedDate //jpa에서 엔티티 생성 시간 처리
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate //최종 수정 시간 자동으로 처리
    @Column(name = "moddate")
    private LocalDateTime modDate;


}
