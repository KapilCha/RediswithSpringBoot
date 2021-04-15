package com.example.springbootrediscache.repository;

import java.util.Set;

import com.example.springbootrediscache.models.User;

public interface UserRepository {
    void save(String name,User user);
    Set<User> findByName(String name);
    void update(String name,User user);
    
}
