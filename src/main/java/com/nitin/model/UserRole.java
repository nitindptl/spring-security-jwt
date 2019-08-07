package com.nitin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="USER_ROLES1")
@Entity
public class UserRole {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
	/*
	@OneToMany(mappedBy="userRoles")
	@Column(name="USER_ID")
	private List<User> user;
	
	@OneToMany(mappedBy="userRoles")
	@Column(name="ROLE_ID")
	private List<Role> role;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Role> getRole() {
		if(role == null)
			role = new ArrayList<Role>();
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

*/}
