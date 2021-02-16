package com.learning.ms.user.model;

import com.learning.ms.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDepartmentResponse {
    private User user;
    private Department department;
}
