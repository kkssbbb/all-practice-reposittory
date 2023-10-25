package com.firstwebservice.firstwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //JPA Auditing 활성화
@SpringBootApplication //스프링부트의 자동설정, 스프링 Bean 읽기와 생성 자동설정
public class FirstwebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstwebserviceApplication.class, args);
		//SpringApplication.run 로인해 내장 WAS 를실행
	}

}
