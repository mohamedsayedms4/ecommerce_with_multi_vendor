package org.example.test1.Repo;

import org.example.test1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String username);
}
