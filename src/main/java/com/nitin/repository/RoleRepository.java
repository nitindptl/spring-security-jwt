package com.nitin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nitin.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
