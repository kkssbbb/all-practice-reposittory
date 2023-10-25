package com.kkssbbb.blogs.model;

import com.kkssbbb.blogs.dto.ReplySaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private  User user;

    @CreationTimestamp
    private LocalDateTime createData;

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", board=" + board +
                ", user=" + user +
                ", createData=" + createData +
                '}';
    }
}
