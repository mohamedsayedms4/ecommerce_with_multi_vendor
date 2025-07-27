package org.example.springjpa.service;


import org.example.springjpa.model.Department;
import org.example.springjpa.model.Employee;
import org.example.springjpa.repo.DeptRepo;
import org.example.springjpa.repo.EmployeeRpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
    @Autowired
    private DeptRepo deptRepo;

    public Department gDepartment(Integer id) {
        return deptRepo.findById(id).orElseThrow();
    }
}
