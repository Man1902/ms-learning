package com.learning.ms.user.service;

import com.learning.ms.user.entity.User;
import com.learning.ms.user.model.UserDepartmentResponse;

public interface UserService {
    User saveUser(User user);

    UserDepartmentResponse getUser(Long userId);
}
