package com.excelseven.backoffice.repository;

import com.excelseven.backoffice.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyLikesRepository extends JpaRepository<ReplyLike, Long> {

    Optional<ReplyLike> findByUserAndReply(User user, Reply reply);
}
