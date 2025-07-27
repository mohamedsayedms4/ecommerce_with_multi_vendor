package org.example.sprigjpa1.service;

import org.example.sprigjpa1.model.Role;
import org.example.sprigjpa1.model.User;
import org.example.sprigjpa1.repository.RoleRepository;
import org.example.sprigjpa1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository RoleRepository;

    public Role findById(Integer id) {
        return RoleRepository.findById(id).orElse(null);
    }


    public List<Role> findAll() {
        return RoleRepository.findAll();
    }



    public Role save(Role Role) {
        return RoleRepository.save(Role);
    }



    public void deleteById(Integer id) {
        RoleRepository.deleteById(id);
    }





}
