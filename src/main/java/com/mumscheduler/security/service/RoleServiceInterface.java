package com.mumscheduler.security.service;

import com.mumscheduler.security.model.Role;

import java.util.List;

public interface RoleServiceInterface {

	Role save(Role role);
	List<Role> getRoleList();
}
