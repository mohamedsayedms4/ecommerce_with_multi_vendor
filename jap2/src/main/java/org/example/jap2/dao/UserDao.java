package org.example.jap2.dao;

import org.example.jap2.Repo.UserRepo;
import org.example.jap2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public List<User> getAllUsersByFirstName(String firstName) {
        return this.userRepo.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<User> findAllUsersSortedByFirstName() {
        return this.userRepo.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }


}
