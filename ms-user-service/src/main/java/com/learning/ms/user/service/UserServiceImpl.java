package com.learning.ms.user.service;

import com.learning.ms.user.entity.User;
import com.learning.ms.user.model.Department;
import com.learning.ms.user.model.UserDepartmentResponse;
import com.learning.ms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDepartmentResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElse(new User());

        // get department details from ms-department-service
        Department department = restTemplate.getForObject("http://ms-department-service/api/departments/" + user.getDepartmentId(), Department.class);

        UserDepartmentResponse userDepartmentResponse = new UserDepartmentResponse();
        userDepartmentResponse.setUser(user);
        userDepartmentResponse.setDepartment(department);
        return userDepartmentResponse;
    }
}
