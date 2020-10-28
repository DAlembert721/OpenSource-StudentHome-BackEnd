package com.acme.studenthome.domain.repository.UserAccountSystemRepository;

import com.acme.studenthome.domain.model.UserAccountSystem.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
