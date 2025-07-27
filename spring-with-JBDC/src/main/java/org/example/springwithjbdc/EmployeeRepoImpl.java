package org.example.springwithjbdc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM Employee";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        return result != null ? result : 0;
    }

    @Override
    public Employee findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, salary FROM Employee WHERE id = ?",
                new EmpMapper(), id
        );
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, salary FROM Employee",
                new EmpMapper()
        );
    }

    @Override
    public int insert(Employee employee) {
        return jdbcTemplate.update(
                "INSERT INTO Employee (name, salary) VALUES (?, ?)",
                employee.getName(), employee.getSalary()
        );
    }


    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update(
                "UPDATE Employee SET name = ?, salary = ? WHERE id = ?",
                employee.getName(), employee.getSalary(), employee.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(
                "DELETE FROM Employee WHERE id = ?",
                id
        );
    }
}
