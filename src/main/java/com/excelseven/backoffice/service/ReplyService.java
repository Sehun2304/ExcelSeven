package com.excelseven.backoffice.service;

import com.excelseven.backoffice.dto.ReplyRequestDto;
import com.excelseven.backoffice.dto.ReplyResponseDto;
import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.entity.Reply;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.PostRepository;
import com.excelseven.backoffice.repository.ReplyRepository;
//import com.excelseven.backoffice.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionException;

@Service
@AllArgsConstructor

public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyResponseDto createReply(ReplyRequestDto requestDto, User user) {//댓글 생성
        Post post = postRepository.findById(requestDto.getPostId()).get();   //PostId로 게시글 찾음
        Reply reply = new Reply(requestDto.getContent(), user, post);  //댓글내용,작성자,작성글 담음

        replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }
    public ReplyResponseDto updateReply(Long replyId, ReplyRequestDto requestDto, User user) {
        Reply reply = replyRepository.findById(replyId).orElseThrow();
        if (!reply.getUser().getId().equals(user.getId())) {  //작성자와 같은지 체크
            throw new RejectedExecutionException("작성자만 수정 가능합니다");
        }
        reply.setContent(requestDto.getContent());//동일하면 수정댓글을 reply에 넣어줌
        replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }
    public void deleteReply(Long id, User user) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다"));
        if (!reply.getUser().getId().equals(user.getId())) {  //작성자와 같은지 체크
            throw new RejectedExecutionException("작성자만 삭제 가능합니다");
        }
        replyRepository.delete(reply);


        //1 reply.getUser().equals(user) => 그냥 false !를 쓰면 true로 나옴
        //2 우리가 원하는 것은 정상이면 reply.getUser().equals(user) => 그냥 true !를 쓰면 false로 나옴
    }
}