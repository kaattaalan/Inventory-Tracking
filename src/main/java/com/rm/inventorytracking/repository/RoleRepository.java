package com.rm.inventorytracking.repository;

import org.springframework.data.repository.CrudRepository;

import com.rm.inventorytracking.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByRole(String role);
}
