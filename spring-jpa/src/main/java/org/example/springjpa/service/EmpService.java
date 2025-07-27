package org.example.springjpa.service;


import org.example.springjpa.model.Employee;
import org.example.springjpa.repo.EmployeeRpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    private EmployeeRpo employeeRpo;

    public Employee getEmployeeById(Integer id) {
        return employeeRpo.findById(id).orElseThrow();
    }

    public List<Employee> getEmpByName(String name) {
        return employeeRpo.filterNative(name);
    }

    public List<Employee> getEmpByDepartment(Integer id) {
        return employeeRpo.findByDepartment_Id(id);
    }
}
