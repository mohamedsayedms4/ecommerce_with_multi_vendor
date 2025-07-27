package org.example.springwithjbdc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    // ✔ Count
    @GetMapping("/count")
    public int count() {
        return employeeRepo.count();
    }

    // ✔ Find by ID
    @GetMapping("/id={id}")
    public Employee findById(@PathVariable int id) {
        return employeeRepo.findById(id);
    }

    // ✔ Find All
    @GetMapping("/all")
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    // ✔ Insert new Employee
    @PostMapping("/add")
    public String insert(@RequestBody Employee employee) {
        int rows = employeeRepo.insert(employee);
        return rows == 1 ? "Employee added successfully" : "Insert failed";
    }



    // ✔ Update existing Employee (by ID)
    @PutMapping("/update")
    public String update(@RequestBody Employee employee) {
        int rows = employeeRepo.update(employee);
        return rows == 1 ? "Employee updated successfully" : "Update failed";
    }

    // ✔ Delete employee by ID
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        int rows = employeeRepo.delete(id);
        return rows == 1 ? "Employee deleted successfully" : "Delete failed";
    }
}
