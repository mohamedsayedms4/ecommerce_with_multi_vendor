package org.example.sprigjpa1.service;

import org.example.sprigjpa1.model.Department;
import org.example.sprigjpa1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
