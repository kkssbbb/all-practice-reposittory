package com.example.ex2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_memo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mId;

    @Column(length = 200, nullable = false)
    private String memoText;
}
