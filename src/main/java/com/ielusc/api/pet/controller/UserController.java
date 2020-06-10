package com.ielusc.api.pet.controller;

import com.ielusc.api.pet.model.User;
import com.ielusc.api.pet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity listaUsuarios(){

        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity criaUsuario(@RequestBody User user){
        return new ResponseEntity(userRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping (path = {"{uid}"})
    public ResponseEntity buscaUsuario(@PathVariable long uid){
        Optional<User> user = userRepository.findById(uid);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }









    @PutMapping (value = {"{uid}"})
    public ResponseEntity atualizaUsuario(@PathVariable("id") long uid){
        Optional<User> user = userRepository.findById(uid);

        if (user.isPresent()) {
            return new ResponseEntity(userRepository.save(user), HttpStatus.CREATED);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path ={"{uid}"})
    public ResponseEntity deletaUsuario(@PathVariable("id") long uid) {
        Optional<User> user = userRepository.findById(uid);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
