package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User addUser(User user);
    Iterable<User> getUsers();

    List<String> getUsernames();
    User getUserByUsername(String username);

    User getUserById(long id);
    Map<String, List<Room>> numberOfRoomsByType(long userId);




}