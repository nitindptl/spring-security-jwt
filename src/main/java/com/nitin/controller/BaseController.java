package com.nitin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.nitin.model.User;
import com.nitin.service.impl.UserServiceImpl;

public class BaseController {
	
	@Autowired
    private UserServiceImpl userDetailsService;
	
	public User getCurrentUser() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetails) {
    	  username = ((UserDetails)principal).getUsername();
    	} else {
    	  username = principal.toString();
    	}
		return userDetailsService.findOne(username);
		
	}

}
