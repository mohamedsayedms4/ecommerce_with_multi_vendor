package org.example.sprigjpa1.service;

import org.example.sprigjpa1.model.Employee;
import org.example.sprigjpa1.model.User;
import org.example.sprigjpa1.repository.EmployeeRepository;
import org.example.sprigjpa1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public User findById(Integer id) {
        return UserRepository.findById(id).orElse(null);
    }


    public List<User> findAll() {
        return UserRepository.findAll();
    }



    public User save(User User) {
        return UserRepository.save(User);
    }



    public void deleteById(Integer id) {
        UserRepository.deleteById(id);
    }





}
