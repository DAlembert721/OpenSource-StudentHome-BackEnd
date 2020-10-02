package com.acme.studenthome.service.UserAccountSystem;

import com.acme.studenthome.domain.model.SuscriptionsSystem.Subscription;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.model.UserAccountSystem.User;
import com.acme.studenthome.domain.repository.SubscriptionSystemRepository.SubscriptionRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.LandLordRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.UserRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.LandLordService;
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
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        return landLordRepository.findByIdAndUserId(landLordId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("LandLord", "Id", landLordId));
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
    public LandLord updateLandLord(Long userId, Long landLordId, LandLord landLordRequest) {
        if (!landLordRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        LandLord landLord = landLordRepository.findById(landLordId)
                .orElseThrow(() -> new ResourceNotFoundException("LandLord", "Id", landLordId));
        landLord.setDni(landLordRequest.getDni());
        landLord.setFirstName(landLordRequest.getFirstName());
        landLord.setLastName(landLordRequest.getLastName());
        landLord.setPhone(landLordRequest.getPhone());
        landLord.setSubscription(landLordRequest.getSubscription());
        return landLordRepository.save(landLord);
    }

    @Override
    public ResponseEntity<?> deleteLandLord(Long userId, Long landLordId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        LandLord landLord = landLordRepository.findByIdAndUserId(landLordId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", landLordId));
        landLordRepository.delete(landLord);
        return ResponseEntity.ok().build();
    }
}
