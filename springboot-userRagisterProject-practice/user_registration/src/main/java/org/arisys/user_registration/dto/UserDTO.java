package org.arisys.user_registration.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long userId;
    private String name;
    private Integer  age;
    private String gender;
    private String job;
    private LocalDateTime legDate,modDate;
}
