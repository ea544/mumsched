package com.mumscheduler.security.service;

import com.mumscheduler.security.model.User;
import com.mumscheduler.security.repository.RoleRepository;
import com.mumscheduler.security.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserServiceInterface{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	/**
	 * Save a user record. Use this when you create a new user
	 * @param user
	 * @param role
	 * @return null if user exists, User if this is new user
	 */
	public User save(User user, String role) {
		if( userRepository.findByEmail(user.getEmail()) != null) {
			return null;
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(1);
		user.setRole(roleRepository.findByRole(role));
		return userRepository.save(user);
	}
	
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
