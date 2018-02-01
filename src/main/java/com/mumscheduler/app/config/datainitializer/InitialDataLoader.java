package com.mumscheduler.app.config.datainitializer;

import com.mumscheduler.security.model.Role;
import com.mumscheduler.security.model.User;
import com.mumscheduler.security.service.RoleService;
import com.mumscheduler.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource("classpath:mumsched.properties")
@Component
public class InitialDataLoader implements ApplicationRunner {

	private UserService userService;

	private RoleService roleService;
	
	private Environment env;
	
	@Autowired
	public InitialDataLoader(UserService userService, RoleService roleService, Environment env) {
		this.userService = userService;
		this.roleService = roleService;
		this.env = env;
	}
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		/**
		 * Initialize roles
		 */
		roleService.save(new Role("ADMIN"));
		
		/**
		 * Initialize the database with super user admin
		 */
		
		User user = new User("admin", "admin");
		user.setEmail(env.getProperty("mumsched.admin.email"));
		user.setPassword(env.getProperty("mumsched.admin.password"));
		userService.save(user, "ADMIN");
		
	}
	
}
