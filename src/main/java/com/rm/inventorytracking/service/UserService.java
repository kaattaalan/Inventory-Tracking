package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.User;

public interface UserService {
    User addUser(User user);
    Iterable<User> getUsers();
}