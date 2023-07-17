package com.excelseven.backoffice.repository;

import com.excelseven.backoffice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
