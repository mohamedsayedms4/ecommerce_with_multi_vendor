package org.example.springjpa.control;

import org.example.springjpa.model.Department;
import org.example.springjpa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("department")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Integer id) {
        return deptService.gDepartment(id);
    }
}
