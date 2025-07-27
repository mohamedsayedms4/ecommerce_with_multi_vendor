package org.example.sprigjpa1.service;

import org.example.sprigjpa1.model.Employee;
import org.example.sprigjpa1.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee update(Employee employee) {
        Employee currentEmployee = employeeRepository.findById(employee.getId()).get();
        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        currentEmployee.setSalary(employee.getSalary());
        currentEmployee.setDepartment(employee.getDepartment());
        return employeeRepository.save(currentEmployee);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }


    public List<Employee> findByDepartment(int departmentId) {
        return employeeRepository.findByDepartment(departmentId);
    }

    public List<Employee> findByDepartmentName(@Param("deptName")String deptName) {
        return employeeRepository.findByDepartmentName(deptName);
    }

    public Optional<Employee> login(String userName, String password) {
        Optional<Employee> employee = employeeRepository.findByUserName(userName);
        if (employee.isPresent() && employee.get().getPassword().equals(password)) {
            logger.info("Logged in user: " + userName);
            return employee;
        }
        logger.error("this email is {}", employee.get().getUserName());
        return Optional.empty();
    }


    public Optional<Employee> findByIdOptional(Long id) {
        return employeeRepository.findById(id);
    }



}
