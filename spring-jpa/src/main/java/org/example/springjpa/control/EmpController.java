package org.example.springjpa.control;


import org.example.springjpa.model.Employee;
import org.example.springjpa.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employee")
public class EmpController {

    @Autowired
    EmpService empService;

    @GetMapping("/id/{id}")
    public Employee getEmpById(@PathVariable Integer id) {
        return empService.getEmployeeById(id);
    }

    @GetMapping("/name/{name}")
    public List<Employee> getEmpByName(@PathVariable String name) {
        return empService.getEmpByName(name);
    }

    @GetMapping("/dept/{id}")
    public List<Employee> getEmpByDept(@PathVariable Integer id) {
        return empService.getEmpByDepartment(id);
    }
}
