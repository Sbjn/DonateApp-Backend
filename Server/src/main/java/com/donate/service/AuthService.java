package com.donate.service;

import com.donate.Dtofiles.LoginDto;
import com.donate.Dtofiles.ResgisterDto;
import com.donate.Dtofiles.UserDto;

public interface AuthService {
	
	String login(LoginDto loginDto);
	
	
	String register (ResgisterDto resgisterDto);
	
	
	//get user by email
	
	UserDto getUserByEmail(String email);
}
