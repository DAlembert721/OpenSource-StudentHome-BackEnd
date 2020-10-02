package com.acme.studenthome.service.UserAccountSystem;

import com.acme.studenthome.domain.model.UserAccountSystem.User;

import com.acme.studenthome.domain.repository.UserAccountSystemRepository.UserRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.UserService;

import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->
                        new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return null;
    }
}
