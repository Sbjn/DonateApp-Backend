package com.donate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import com.donate.Dtofiles.UserDto;
import com.donate.Exception.APIResponse;
import com.donate.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
			
		@Autowired
		private UserService userService;
	
	
		//Create	
			@PostMapping("/")
			public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
				UserDto createUser = this.userService.createUser(userDto);
				return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
			}
	
		//Update
			@PutMapping("/update/{userId}")
			public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId){
				UserDto updateUser = this.userService.updateUser(userDto, userId);
				return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
			}
		
		//delete
			
			@DeleteMapping("/delete/{userId}")
			public APIResponse deleteUser(@PathVariable Integer userId) {
				this.userService.deleteUser(userId);
				return new APIResponse("User is sucssesfully deleted",true);
			}
		//get user by Id
			
			@GetMapping("/{userId}")
			public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
				UserDto userById = this.userService.getUserById(userId);
				return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
			}
			
		//getAll
			@GetMapping("/")
			public ResponseEntity<List<UserDto>> getAllUser(@RequestBody UserDto userDto){
				List<UserDto> allUser = this.userService.getAllUser();
				return new ResponseEntity<List<UserDto>>(allUser, HttpStatus.OK);
			}
}
