package com.rm.inventorytracking.service;

import java.util.List;

import com.rm.inventorytracking.domain.User;

public interface UserService {
    User addUser(User user);
    Iterable<User> getUsers();

    List<String> getUsernames();
    User getUserByUsername(String username);
    
    void deleteUserById(Long id);

    User getUserById(long id);

}