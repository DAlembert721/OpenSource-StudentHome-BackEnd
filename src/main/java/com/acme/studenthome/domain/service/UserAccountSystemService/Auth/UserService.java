package com.acme.studenthome.domain.service.UserAccountSystemService.Auth;

import com.acme.studenthome.domain.model.UserAccountSystem.Auth.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(User user);
    User getUserById(Long userId);
    ResponseEntity<?> deleteUser(Long userId);


}
