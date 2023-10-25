package com.kkssbbb.blogs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {

    private int userId;
    private int boardId;
    private String content;
}
