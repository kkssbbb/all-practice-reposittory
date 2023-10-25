package com.crud.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //jpa를 이용하면서 AuditingEntityListener를 활성하 시키기 위해 추가
public class GuestBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestBookApplication.class, args);
    }

}
