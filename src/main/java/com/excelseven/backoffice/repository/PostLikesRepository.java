package com.excelseven.backoffice.repository;

import com.excelseven.backoffice.entity.Post;
import com.excelseven.backoffice.entity.PostLike;
import com.excelseven.backoffice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikesRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserAndPost(User user, Post post);
}
