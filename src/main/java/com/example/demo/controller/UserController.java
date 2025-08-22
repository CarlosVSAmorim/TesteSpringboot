package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")

public class UserController {

    private final UserRepository repository;

    // Injeção de dependência (Spring cria o objeto Repository pra gente)
    public UserController(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = repository.findById(id);
        return  user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // GET - listar todos os usuários
    @GetMapping
    public List<User> getAll(){ return repository.findAll(); }

    // POST - criar novo usuário
    @PostMapping
    public User createUser(@RequestBody User user){
        return repository.save(user);
    }

    // PUT - atualizar usuário existente
    @PutMapping("/{id}")
    public User atualizar(@PathVariable Long id, @RequestBody User user){
    User u = repository.findById(id).orElseThrow();
    u.setNome(user.getNome());
    u.setEmail(user.getEmail());
    return repository.save(u);
    }

    // DELETE - remover usuário
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }




}
