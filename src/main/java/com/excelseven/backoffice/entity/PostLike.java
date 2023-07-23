package com.excelseven.backoffice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostLikes_id")
    private Long id;


    @Enumerated(EnumType.STRING)
    private LikeStatus likeStatus;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Post_id")
    private Post post;

    public PostLike(User user, Post post, LikeStatus likeStatus) {
        this.user = user;
        this.post = post;
        this.likeStatus = likeStatus;
    }
}
