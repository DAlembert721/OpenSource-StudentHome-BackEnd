package com.acme.studenthome.domain.repository.UserAccountSystemRepository.Auth;

import com.acme.studenthome.domain.model.UserAccountSystem.Auth.ERole;
import com.acme.studenthome.domain.model.UserAccountSystem.Auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
