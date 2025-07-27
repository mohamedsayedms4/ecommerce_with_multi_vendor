package org.example.sprigjpa1.controller;

import org.example.sprigjpa1.model.Department;
import org.example.sprigjpa1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public Department findById(@PathVariable Integer id) {
        return departmentService.findById(id);
    }
}
