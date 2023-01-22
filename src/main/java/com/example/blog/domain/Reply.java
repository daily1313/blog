package com.example.blog.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// Reply 클래스가 MySQL에 테이블이 생성이 된다.
// ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;

    // 하나의 게시글은 여러개의 답변 가능
    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    // 여려개의 답글을 하나의 유저가 쓸 수 있다.
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    // 날짜 및 시간이 자동적으로 입력
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Reply [id=" + id + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
                + createDate + "]";
    }
}
