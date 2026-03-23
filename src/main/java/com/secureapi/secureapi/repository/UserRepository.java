package com.secureapi.secureapi.repository;

import com.secureapi.secureapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Map<String, User> users = new HashMap<>();

    public User findByUsername(String username) {
        return users.get(username);
    }

    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    public boolean exists(String username) {
        return users.containsKey(username);
    }
}