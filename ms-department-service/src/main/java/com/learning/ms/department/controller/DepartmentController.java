package com.learning.ms.department.controller;

import com.learning.ms.department.entity.Department;
import com.learning.ms.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        log.info("Inside saveDepartment method of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{departmentId}")
    public Department getDepartment(@PathVariable Long departmentId) {
        log.info("Inside getDepartment method of DepartmentController");
        return departmentService.getDepartment(departmentId);
    }
}
