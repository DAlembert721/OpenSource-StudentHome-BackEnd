package com.acme.studenthome.domain.repository.UserAccountSystemRepository;

import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandLordRepository extends JpaRepository<LandLord, Long> {
    Optional<LandLord> findByIdAndUserId(Long landLordId, Long userId);
}
