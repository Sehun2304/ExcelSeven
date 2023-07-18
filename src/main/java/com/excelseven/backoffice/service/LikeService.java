package com.excelseven.backoffice.service;

import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.entity.PostLike;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.*;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {


    private final PostRepository postRepository;

    private final ReplyRepository replyRepository;

    private final PostLikesRepository postLikesRepository;

    private final ReplyLikesRepository replyLikesRepository;

    public void likePost(User user, Long postId) {

        Post post = findPost(postId);

        if (postLikesRepository.findByUserAndPost(user, post).isPresent()){
            throw new DuplicateRequestException("이미 좋아요한 게시글입니다.");
        } else {
            PostLike postLike = new PostLike(user, post);
            postLikesRepository.save(postLike);
        }
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("선택한 게시글이 존재하지 않습니다."));
    }
}
