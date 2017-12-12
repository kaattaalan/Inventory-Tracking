package com.rm.inventorytracking.controller;


import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;



@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        //valid ve bindingresult'ı form validationı için kullanıyoruz.
        if (bindingResult.hasErrors())
        {
            return "register";
        }


        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping("/users")
    //userpage'e yönlendirme
    public ModelAndView getUsersPage() {
        return new ModelAndView("users", "users", userService.getUsers());
    }
}