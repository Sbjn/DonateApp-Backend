package com.donate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donate.Dtofiles.JWTAuthResponse;
import com.donate.Dtofiles.LoginDto;
import com.donate.Dtofiles.ResgisterDto;
import com.donate.Dtofiles.UserDto;
import com.donate.service.AuthService;


@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
	
	
	private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    
    //Login
    
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
    	String token = this.authService.login(loginDto);
    	UserDto userByEmail = authService.getUserByEmail(loginDto.getUsername());
    	
    	
    	JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
    	jwtAuthResponse.setAccessToken(token);
    	jwtAuthResponse.setEmail(loginDto.getUsername());
    	jwtAuthResponse.setId(userByEmail.getId());
    	jwtAuthResponse.setUsername(userByEmail.getUsername());
    	
    	return ResponseEntity.ok(jwtAuthResponse);
    }
	
    //register
    
    @PostMapping(value = {"/register","/signup"})
	public ResponseEntity<String> register(@RequestBody ResgisterDto resgisterDto){
		String register = this.authService.register(resgisterDto);
		return new ResponseEntity<>(register,HttpStatus.CREATED);
	}

    
}
