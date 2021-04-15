package com.example.springbootrediscache.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.springbootrediscache.models.User;

@Repository
public class UserRepositoryImpl implements  UserRepository {

    private RedisTemplate<String, User> redisTemplate;
    
    Map<String, Set<User>> setMultimap ;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.setMultimap = new HashMap<String, Set<User>>();
    }
    
    @Override
    public void save(String name, User user) {
        Set<User> set = setMultimap.get(name);
        if (set == null) {
            set = new HashSet<>();
            setMultimap.put(name, set);
        }
        set.add(user);
    }

    @Override
    public Set<User> findByName(String name) {
    	return setMultimap.get(name);
    }

    @Override
    public void update(String name,User user) {
      save(name, user);
    }

}
