package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
     this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public User create(User user) {
        return repository.save(user);
    }
    public User update(long id,User user) {
        User u = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        u.setNome(user.getNome());
        u.setEmail(user.getEmail());
        return repository.save(u);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
