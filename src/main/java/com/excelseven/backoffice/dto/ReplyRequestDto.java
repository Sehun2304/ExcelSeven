package com.excelseven.backoffice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class ReplyRequestDto {
    Long postId;    //게시글 찾기용
    String content;    //댓글 내용

    public void setContent(String reply){
        this.content = content;
    }
}