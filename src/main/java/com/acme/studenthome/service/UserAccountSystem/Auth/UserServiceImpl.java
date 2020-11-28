package com.acme.studenthome.service.UserAccountSystem.Auth;

import com.acme.studenthome.domain.model.UserAccountSystem.Auth.User;

import com.acme.studenthome.domain.repository.UserAccountSystemRepository.Auth.UserRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.Auth.UserService;

import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            return ResponseEntity
                    .badRequest()
                    .body(String.format("Error: The user with email %s already exist", user.getUsername()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully! ");
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
