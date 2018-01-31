package com.mumscheduler.app.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class DBSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private MUMSchedAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT email, password, active FROM user WHERE email=?")
			.authoritiesByUsernameQuery("SELECT u.email, r.role from user u "
					+ "INNER JOIN role r ON u.role_id = r.id WHERE email=?")
			.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/", "/home").permitAll()
		.antMatchers("/standard/**", "/img/**").permitAll()
		.antMatchers("/admin").hasRole("ADMIN")
		//.anyRequest().permitAll() //use this for development so you can access all the pages
		.anyRequest().authenticated()
		.and()
		.formLogin().successHandler(authenticationSuccessHandler)
			.loginPage("/login").permitAll()
			//.defaultSuccessUrl("/dashboard")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/home");
		http.exceptionHandling().accessDeniedPage("/403");
	}
}
