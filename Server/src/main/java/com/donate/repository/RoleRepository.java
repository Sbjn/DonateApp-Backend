package com.donate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donate.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
		
	Optional<Role> findByName(String name);
	
}
