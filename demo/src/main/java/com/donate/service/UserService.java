package com.donate.service;

import java.util.List;

import com.donate.Dtofiles.UserDto;

public interface UserService {
	
	
	//Create	
		
	UserDto createUser (UserDto userDto);
	
	//Update
	
	UserDto updateUser (UserDto userDto, Integer userId);
	//delete
	
	void deleteUser(Integer userId);
	
	
	
	//get user by Id
	
	UserDto getUserById(Integer userId);
	
	//get user by email
	
	UserDto getUserByEmail(String email);
	
	//getAll
	
	List<UserDto> getAllUser();
}
