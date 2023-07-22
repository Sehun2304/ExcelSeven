package com.excelseven.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private int state;
    private UserDto user;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String content;

    // 생성자, 게터, 세터 등 필요한 코드 추가

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    // 사용자 정보를 담는 UserDto 클래스 (내부 클래스)
    public static class UserDto {
        private Long id;
        private String username;
        private String password;
        private String introduction;
        private String image;

        // 생성자, 게터, 세터 등 필요한 코드 추가
    }
}