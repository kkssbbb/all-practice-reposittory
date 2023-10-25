package com.firstwebservice.firstwebservice.domain.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 사용자의 권한을 관리하는 Enum 클래스
@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST","손님"),
        USER("ROLE_USER","일반 사용자");
//    GUEST("ROLE_GUEST","손님"),
//    USER("ROLE_USER"."일반 사용자");

    private final String Key;
    private final String title;
}
