package com.rm.inventorytracking.service;

import java.util.List;
import java.util.Map;

import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.domain.User;

public interface UserService {
    User addUser(User user);
    Iterable<User> getUsers();

    List<String> getUsernames();
    User getUserByUsername(String username);
    
    void deleteUserById(Long id);

    User getUserById(long id);
    Map<String, List<Room>> numberOfRoomsByType(long userId);




}