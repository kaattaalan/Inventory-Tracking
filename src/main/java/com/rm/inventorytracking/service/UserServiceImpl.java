package com.rm.inventorytracking.service;


import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;





@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }





    @Override //user ekleme
    public User addUser(User user) {
        return userRepository.save(user);
    }

    //userları çekiyoruz.
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }






}