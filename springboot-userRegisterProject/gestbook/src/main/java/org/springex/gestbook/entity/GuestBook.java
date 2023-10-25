package org.springex.gestbook.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class GuestBook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long gno;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 1500, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String writer;

    //엔티티 클래스는 가능하면 setter기능을 만들지 않는것이 권장 사항이다.
    public void changeTitle(String title) {
        this.title =title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
