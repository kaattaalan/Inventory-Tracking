package com.rm.inventorytracking.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.RoleRepository;
import com.rm.inventorytracking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	private final UserRepository userRepository;
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override // user ekleme
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	// userları çekiyoruz.

	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	public List<String> getUsernames() {
		List<String> usennames = new ArrayList<String>();
		Iterator iterator = getUsers().iterator();

		while (iterator.hasNext()) {
			User user = (User) iterator.next();
			usennames.add(user.getUsername());
		}
		return usennames;
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("User with username: " + username + " not found.");
		} else {
			return user;
		}
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.delete(id);

	}

}