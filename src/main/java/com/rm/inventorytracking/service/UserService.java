package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    Iterable<User> getUsers();
    List<String> getUsernames();

    User getUserByUsername(String username);
}