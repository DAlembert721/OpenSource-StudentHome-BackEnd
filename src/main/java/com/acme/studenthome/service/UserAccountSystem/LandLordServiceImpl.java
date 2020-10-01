package com.acme.studenthome.service.UserAccountSystem;

import com.acme.studenthome.domain.model.SuscriptionsSystem.Subscription;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.model.UserAccountSystem.User;
import com.acme.studenthome.domain.repository.SubscriptionSystemRepository.SubscriptionRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.LandLordRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.UserRepository;
import com.acme.studenthome.domain.service.UserAccountSystem.LandLordService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LandLordServiceImpl implements LandLordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LandLordRepository landLordRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public LandLord getLandLordByIdAndUserId(Long landLordId, Long userId) {
        return null;
    }

    @Override
    public LandLord createLandLord(Long userId, Long subscriptionId, LandLord landLord) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "Id", userId));
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subscription", "Id", subscriptionId));
        landLord.setUser(user);
        landLord.setSubscription(subscription);
        return landLordRepository.save(landLord);
    }

    @Override
    public LandLord updateLandLord(Long userId, Long landLordId, Student landLordRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteLandLord(Long userId, Long landLordId) {
        return null;
    }
}
