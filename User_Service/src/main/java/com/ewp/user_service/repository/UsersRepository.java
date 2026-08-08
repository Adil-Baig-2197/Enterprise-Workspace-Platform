package com.ewp.user_service.repository;

import com.ewp.user_service.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID>, JpaSpecificationExecutor<Users> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndUserIdNot(String email,UUID userId);
    Page<Users> findByName(String name,Pageable pageable);
}