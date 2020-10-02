package com.acme.studenthome.domain.service.SubscriptionSystemService;

import com.acme.studenthome.domain.model.SuscriptionsSystem.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {
    Page<Subscription> getAllSubscriptions(Pageable pageable);
}
