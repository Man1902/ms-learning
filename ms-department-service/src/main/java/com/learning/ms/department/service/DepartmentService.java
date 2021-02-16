package com.learning.ms.department.service;

import com.learning.ms.department.entity.Department;

public interface DepartmentService {
    Department saveDepartment(Department department);

    Department getDepartment(Long departmentId);
}
