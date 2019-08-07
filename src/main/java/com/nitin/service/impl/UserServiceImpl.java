package com.nitin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nitin.dto.UserDto;
import com.nitin.model.Role;
import com.nitin.model.User;
import com.nitin.repository.RoleRepository;
import com.nitin.repository.UserRepository;
import com.nitin.service.UserService;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        
       // List<Long> rolesIds  = user.getRoles().stream().map(Role::getId).collect(Collectors.toList());
       // List<Role> roles  = (List<Role>) roleRepository.findAllById(rolesIds);
        user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		newUser.setSalary(user.getSalary());
		newUser =  userRepo.save(newUser);
		Role role = new Role();
		//TODO:Later changes- do not set default role
		role.setId(1);//Admin Role
		role.setUser(Arrays.asList(newUser));
		newUser.setRoles(new HashSet<>(Arrays.asList(role)));
        newUser =  userRepo.save(newUser);
        return newUser;
    }
}
