package com.ewp.user_service.repository;

import com.ewp.user_service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndUserIdNot(String email,UUID userId);
}