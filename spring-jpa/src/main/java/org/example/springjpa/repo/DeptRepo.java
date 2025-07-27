package org.example.springjpa.repo;

import org.example.springjpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepo extends JpaRepository<Department,Integer> {
}
