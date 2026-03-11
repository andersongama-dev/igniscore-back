package com.igniscore.api.service;

import com.igniscore.api.model.User;
import com.igniscore.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User updateUserCompany(Integer id, Integer company) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setCompany(company);
        return repository.save(user);
    }
}