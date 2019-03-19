package com.rm.inventorytracking.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.service.UserService;

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
			return new ModelAndView("userRooms", "rooms", userService.numberOfRoomsByType(id));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/users/add")
	public ModelAndView getAddUserPage() {
		return new ModelAndView("adduser", "user", new User());

	}

	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public String handleAddRoomForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "redirect:/users/add";

		userService.addUser(user);
		return "redirect:/users";

	}

	@RequestMapping(value = "/users/delete/{id}")
	public ModelAndView deleteUser(@PathVariable Long id) {
		if (null != userService.getUserById(id)) {
			userService.deleteUserById(id);
		}
		return new ModelAndView("users", "users", userService.getUsers());
	}

}