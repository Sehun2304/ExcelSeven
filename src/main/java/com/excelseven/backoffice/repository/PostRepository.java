package com.excelseven.backoffice.repository;

import com.excelseven.backoffice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
