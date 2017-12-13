package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserById(long id);

    User getUserByUsername(String username);

    User addUser(User user);

    Iterable<User> getUsers();

    Map<String, List<Item>> numberOfItemsByType(long userId);

    List<String> getUsernames();
}
