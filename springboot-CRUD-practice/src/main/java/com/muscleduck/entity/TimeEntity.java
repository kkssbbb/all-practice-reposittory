package com.muscleduck.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class TimeEntity {

    @CreatedDate
    @Column(name = "crdate", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "modate")
    private LocalDateTime modifiedDate;
}
