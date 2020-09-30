package com.acme.studenthome.controller.UserAccountSystem;

import com.acme.studenthome.domain.model.UserAccountSystem.User;
import com.acme.studenthome.domain.service.UserAccountSystem.UserService;
import com.acme.studenthome.resource.UserAccountSystem.SaveUserResource;
import com.acme.studenthome.resource.UserAccountSystem.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @PostMapping("/users")
    public  UserResource createUser(@Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }


    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
