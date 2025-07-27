package org.example.sprigjpa1.repository;

import org.example.sprigjpa1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
