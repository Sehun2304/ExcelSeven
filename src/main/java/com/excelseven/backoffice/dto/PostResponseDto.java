package com.excelseven.backoffice.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDto {
    private Long boardId;
    private int state;
    private UserDto user;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String content;
    private List<ReplyDto> replyList;

    // 생성자, 게터, 세터 등 필요한 코드 추가

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    // 내부 클래스 UserDto
    public static class UserDto {
        private Long id;
        private String username;
        private String password;
        private String introduction;
        private String image;

        // 생성자, 게터, 세터 등 필요한 코드 추가
    }

    // 내부 클래스 ReplyDto
    public static class ReplyDto {
        // Reply 정보에 필요한 필드 추가
        // 생성자, 게터, 세터 등 필요한 코드 추가
    }
}
