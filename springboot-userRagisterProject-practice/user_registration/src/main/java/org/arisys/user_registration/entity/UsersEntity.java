package org.arisys.user_registration.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.*;
import jakarta.persistence.*;
import org.arisys.user_registration.dto.UserDTO;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersEntity extends DateEntity{

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    //name
    @Column(length = 100, nullable = false)
    private String name;
    //age
    @Column(nullable = false)
    private  Integer age;
    //gender
    @Column(length = 100, nullable = false)
    private String gender;
    //job
    @Column(length = 100, nullable = false)
    private String job;

    public void changeGender(String gender) {
        this.gender = gender;
    }

    public void updateUserE(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.age = userDTO.getAge();
        this.gender = userDTO.getGender();
        this.job = userDTO.getJob();
    }

}
