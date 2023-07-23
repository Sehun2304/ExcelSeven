package com.excelseven.backoffice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Post")
@EntityListeners(AuditingEntityListener.class) // 날짜 기록을 위해 AuditingEntityListener 등록
public class Post extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Reply> replies = new ArrayList<>();

//    @CreatedDate // 생성일자를 자동으로 기록
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate // 수정일자를 자동으로 기록
//    private LocalDateTime modifiedAt;
}
