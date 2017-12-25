package com.rm.inventorytracking.controller;


import com.rm.inventorytracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Controller
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public ModelAndView getUsersPage() {
        return new ModelAndView("users", "users", userService.getUsers());
    }

    @RequestMapping(value = "/users/{id}/rooms")
    public ModelAndView getUserPage(@PathVariable Long id) {
        if (null == userService.getUserById(id))
            throw new NoSuchElementException("User with id:" + id + " not found");
        else
            return new ModelAndView("userRooms" ,"rooms", userService.numberOfRoomsByType(id));
    }
}