package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    private final UserService service;

    // Injeção de dependência (Spring cria o objeto Repository pra gente)
    public UserController(UserService service){this.service=service;}

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // GET - listar todos os usuários
    @GetMapping
    public List<User> getAll(){ return service.getAll(); }

    // POST - criar novo usuário
    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return service.create(user);
    }

    // PUT - atualizar usuário existente
    @PutMapping("/{id}")
    public User atualizar(@PathVariable Long id,@Valid @RequestBody User user){
    return service.update(id,user);
    }

    // DELETE - remover usuário
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){service.delete(id);
    }




}
