package com.nitin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.dto.UserDto;
import com.nitin.model.User;
import com.nitin.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasPermission(returnObject, 'ADMIN')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser1(){
    	User user = getCurrentUser();
        return userService.findAll();
    }
    
    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasPermission(returnObject, #id)")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }


    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
    	
        return userService.save(user);
    }



}
