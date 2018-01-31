package com.mumscheduler.security.service;

import com.mumscheduler.security.model.User;

public interface UserServiceInterface {
	User findByEmail(String email);
}
