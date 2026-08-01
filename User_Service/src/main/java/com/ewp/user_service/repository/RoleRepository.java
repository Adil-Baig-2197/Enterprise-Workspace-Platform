package com.ewp.user_service.repository;

import com.ewp.user_service.model.Role;
import com.ewp.user_service.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleName name);
}