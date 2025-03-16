package org.akteach.service;

import org.akteach.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public void deleteUser(String id) {
        users.remove(id);
    }
}