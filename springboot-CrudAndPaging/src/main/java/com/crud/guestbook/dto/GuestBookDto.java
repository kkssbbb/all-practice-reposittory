package com.crud.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestBookDto {

    private Long gno;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDate, modDate;


}
