package org.example.test1.service;

import org.example.test1.Repo.UserRepo;
import org.example.test1.model.Product;
import org.example.test1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductService productService;

    public Optional<User> login(String userName , String password){
        Optional<User> user = userRepo.findByUserName(userName);
        return user.filter(u -> u.getPassword().equals(password));
    }

    public User register(User user) {
        return userRepo.save(user);
    }

    public Optional<User> findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }

    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }
}
