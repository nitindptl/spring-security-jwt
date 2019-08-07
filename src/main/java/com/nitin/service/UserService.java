package com.nitin.service;

import java.util.List;

import com.nitin.dto.UserDto;
import com.nitin.model.User;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
