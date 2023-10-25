package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name= "member_id")
    private Long id;

    private String name;

    @Embedded //내장타입 사용했다는 표시
    private Address address;

    @OneToMany(mappedBy = "member") // mappedBy = "member" = order에있는 pk인 member를 읽기만하고 Member에서 수정이 이러나진 않는다는 뜻
    private List<Order> orders = new ArrayList<>();


}
