package com.muscleduck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //날짜 시간처리
public class MuscleDuckApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuscleDuckApplication.class, args);
    }

}
