package org.example.sprigjpa1.repository;

import org.example.sprigjpa1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
