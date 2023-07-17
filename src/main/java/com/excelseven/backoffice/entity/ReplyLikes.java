package com.excelseven.backoffice.entity;

import jakarta.persistence.*;

@Entity
public class ReplyLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReplyLikes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Reply_id")
    private Reply reply;
}
