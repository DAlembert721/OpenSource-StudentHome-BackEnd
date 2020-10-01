package com.acme.studenthome.domain.repository.UserAccountSystemRepository;

import com.acme.studenthome.domain.model.UserAccountSystem.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findByUserId(Long userId, Pageable pageable);

    Optional<Account> findByIdAndUserId(Long accountId, Long userId);
}
