package org.example.springjpa.repo;

import org.example.springjpa.model.Department;
import org.example.springjpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRpo extends JpaRepository<Employee, Integer> {

    // باستخدام Native SQL
    @Query(value = "SELECT * FROM Employee WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    List<Employee> filterNative(@Param("name") String name);


    // أو باستخدام JPQL
    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findByNameJPQL(@Param("name") String name);



    List<Employee> findByDepartment_Id(int id);
}
