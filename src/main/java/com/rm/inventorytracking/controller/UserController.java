package com.rm.inventorytracking.controller;


import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;


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

    /**
     * Controllerda url'deki id'ye sahip kullanıcı olup olmadığını kontrol ediyoruz.
     * Eğer user yoksa exception throw ediyoruz. Varsa numberOfItemsByType metodunun dondurdugu map'i view'a gönderiyoruz.
     * @param id
     * @return
     * @Author: Mehmet Koca
     */
    @RequestMapping(value = "/users/{id}/items")
    public ModelAndView getUserPage(@PathVariable Long id) {
        if (null == userService.getUserById(id)){
            throw new NoSuchElementException("User with id: " + id + " not found.");
        } else {
            return new ModelAndView("userItems","items",userService.numberOfItemsByType(id));
        }
    }
}