package com.kkssbbb.blogs.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity  //User클래스가 MySQL에 자동으로 테이블생성을 하게 해준다.

//@DynamicInsert : insert시에 null인 필드를 제외시켜준다.
public class User {



    @Id  //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.


    private  int id; //시퀸스,auto_increment

    @Column(nullable = false,length =100, unique = true) //null값은 될수없음 30자 까지
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumDefault("user")
    //db에는 RoleType이란게 없기 때문에 아래와같이 이넘타입이 스트링이란걸 알려준다.
    @Enumerated(EnumType.STRING)
    private  RoleType role; // Enum을 쓰는게 좋다. //

    private String oauth; // kakao, google

    @CreationTimestamp
    private LocalDateTime createData;


}
