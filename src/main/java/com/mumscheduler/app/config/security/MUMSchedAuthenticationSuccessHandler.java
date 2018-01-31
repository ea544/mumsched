package com.mumscheduler.app.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MUMSchedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
				Authentication authentication)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
		boolean admin = false;
		boolean student = false;
		boolean faculty = false;
		for(GrantedAuthority auth : authentication.getAuthorities()) {
			if("ADMIN".equals(auth.getAuthority())) {
				admin = true;
			}
			if("STUDENT".equals(auth.getAuthority())) {
				student = true;
			}
			if("FACULTY".equals(auth.getAuthority())) {
				faculty = true;
			}
		}
		
		if(admin) {
			response.sendRedirect("/dashboard");
		}
		else if(student) {
			response.sendRedirect("dashboard");
		}
		else if(faculty) {
			response.sendRedirect("/dashboard");
		}
	}

}
