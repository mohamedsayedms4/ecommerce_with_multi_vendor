package org.example.springwithjbdc;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface EmployeeRepo {


    int count();
    Employee findById(int id);
    List<Employee> findAll();

    int insert(Employee employee);
    int update(Employee employee);
    int delete(int id);

}
