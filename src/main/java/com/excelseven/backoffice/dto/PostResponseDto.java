package com.excelseven.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private int state;
    private String user;
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
    @Getter
    @Setter
    public static class UserDto {
        private Long id;
        private String username;
        private String introduction;
        private String image;

        // 생성자, 게터, 세터 등 필요한 코드 추가

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserDto userDto = (UserDto) o;
            return Objects.equals(id, userDto.id) &&
                    Objects.equals(username, userDto.username) &&
                    Objects.equals(introduction, userDto.introduction) &&
                    Objects.equals(image, userDto.image);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, username, introduction, image);
        }
    }
}
