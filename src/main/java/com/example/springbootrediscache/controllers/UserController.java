package com.example.springbootrediscache.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootrediscache.models.User;
import com.example.springbootrediscache.repository.UserRepository;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    private UserRepository _userRepository;

    public UserController(UserRepository userRepository){
        _userRepository = userRepository;
    }

    @GetMapping("/all/{name}")
    public Set<User> GetAll(@PathVariable("name") final String id){
        return _userRepository.findByName(id);
    }

    @PostMapping("/add")
    public Set<User> add(@RequestBody User user){
        _userRepository.save(user.getName(), new User(user.getId(),user.getName(),80000L));
        return _userRepository.findByName(user.getName());

    }


    @PostMapping("/update")
    public Set<User> update(@RequestBody User user){
        _userRepository.update(user.getName(),new User(user.getId(),user.getName(),1000L));
        return _userRepository.findByName(user.getId());

    }

}