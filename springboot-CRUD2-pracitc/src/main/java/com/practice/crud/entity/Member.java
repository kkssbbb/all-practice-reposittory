package com.practice.crud.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//테이블 과의 맵핑 JPA가 관리 , 엔티티라고 불림
@Builder
@AllArgsConstructor // 여기 필드에 쓴 모든 생성자만 만들어줌
@NoArgsConstructor // 기본 생성자를 만들어줌
@Getter
@ToString
public class Member extends BaseEntity{

    @Id
    private String email;

    private String password;

    private String name;


}
