package org.example.jap2.Repo;

import org.example.jap2.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findByFirstNameContainingIgnoreCase(String firstName);

    default List<User> findAllSortedByFirstName() {
        return findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }
}
