package org.example.jap2.controller;

import org.example.jap2.Repo.UserRepo;
import org.example.jap2.dao.UserDao;
import org.example.jap2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    @GetMapping("/name")
    public List<User> getAllUsersByFirstName(@RequestParam String firstName) {
        return this.userDao.getAllUsersByFirstName(firstName);
    }

    @GetMapping("/sorted")
    public List<User> getSortedUsers() {
        return userDao.findAllUsersSortedByFirstName();
    }

}
