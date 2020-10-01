package com.acme.studenthome.domain.repository.SubscriptionSystemRepository;

import com.acme.studenthome.domain.model.SuscriptionsSystem.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
