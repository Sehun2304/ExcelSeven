package com.excelseven.backoffice.dto;

import com.excelseven.backoffice.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private int postLikes;
    private List<ReplyResponseDto>  replyResponseDtos= new ArrayList<>();

    public void ChangeReplyResponseDtos(List<Reply> replies) {
        for (Reply reply : replies) {
            ReplyResponseDto replyResponseDto = new ReplyResponseDto(reply);
            this.replyResponseDtos.add(replyResponseDto);
        }
    }
}
