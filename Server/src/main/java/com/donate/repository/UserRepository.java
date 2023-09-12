package com.donate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donate.Dtofiles.UserDto;
import com.donate.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByNameOrEmail(String Name, String email);
	
	Boolean existsByusername(String username);
	
	Boolean existsByEmail(String email);

	void save(UserDto userDto);
	
}
