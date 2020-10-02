package com.acme.studenthome.service.SubscriptionSystem;

import com.acme.studenthome.domain.model.SuscriptionsSystem.Subscription;
import com.acme.studenthome.domain.repository.SubscriptionSystemRepository.SubscriptionRepository;
import com.acme.studenthome.domain.service.SubscriptionSystemService.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Page<Subscription> getAllSubscriptions(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }
}
