package com.acme.studenthome.domain.service.UserAccountSystemService;

import com.acme.studenthome.domain.model.UserAccountSystem.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
    ResponseEntity<?> deleteUser(Long userId);


}
