package com.learning.ms.user.controller;

import com.learning.ms.user.entity.User;
import com.learning.ms.user.model.UserDepartmentResponse;
import com.learning.ms.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser method of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{userId}")
    public UserDepartmentResponse getUser(@PathVariable Long userId) {
        log.info("Inside getUser method of UserController");
        return userService.getUser(userId);
    }
}
