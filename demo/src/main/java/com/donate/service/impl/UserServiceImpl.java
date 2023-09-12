package com.donate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donate.Dtofiles.UserDto;
import com.donate.Exception.ResourceNotFoundException;
import com.donate.entity.User;
import com.donate.repository.UserRepository;
import com.donate.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		user.setId(userDto.getId());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		User newUser = this.userRepository.save(user);
		
		return this.modelMapper.map(newUser, UserDto.class);
	}

	
	
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id",	userId));
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		User saveUser = userRepository.save(user);
		
		return this.modelMapper.map(saveUser, UserDto.class);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		
		userRepository.delete(user);
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> listUser = userRepository.findAll();
		List<UserDto> listUserDto = listUser.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return listUserDto;
	}



	@Override
	public UserDto getUserByEmail(String email) {
		User userEmail = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email", "email", email));
		return this.modelMapper.map(userEmail, UserDto.class);
	}




	
}
