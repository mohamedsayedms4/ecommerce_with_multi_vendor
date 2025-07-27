package org.example.sprigjpa1.repository;

import org.example.sprigjpa1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query("SELECT EMP FROM Employee EMP WHERE EMP.firstName LIKE %:firstName%")
    List<Employee> findByFirstName(@Param("firstName") String firstName);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId")
    public List<Employee> findByDepartment(@Param("deptId")int departmentId) ;


    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    public List<Employee> findByDepartmentName(@Param("deptName")String deptName) ;

    Optional<Employee> findByUserName(String userName);
}
