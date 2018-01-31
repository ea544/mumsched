package com.mumscheduler.security.service;

import com.mumscheduler.security.model.Role;
import com.mumscheduler.security.repository.RoleRepository;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService implements RoleServiceInterface {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Role save(Role role) {
		if( roleRepository.findByRole(role.getRole()) == null) {
			return roleRepository.save(role);
		}
		else {
			logger.info((String.format("Tried to add role %s which already exists", role.getRole())));
			return null;
		}
	}
	
	@Override
	public List<Role> getRoleList(){
		return roleRepository.findAll();
	}
}
