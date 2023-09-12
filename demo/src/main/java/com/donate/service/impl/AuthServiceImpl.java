package com.donate.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.donate.Dtofiles.LoginDto;
import com.donate.Dtofiles.ResgisterDto;
import com.donate.Dtofiles.UserDto;
import com.donate.Exception.BlogAPIException;
import com.donate.Exception.ResourceNotFoundException;
import com.donate.entity.Role;
import com.donate.entity.User;
import com.donate.repository.RoleRepository;
import com.donate.repository.UserRepository;
import com.donate.security.JwtTokenProvider;
import com.donate.service.AuthService;


@Service
public class AuthServiceImpl implements AuthService{
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;

	
	
	
	public AuthServiceImpl(	AuthenticationManager authenticationManager,
							UserRepository userRepository,
							RoleRepository roleRepository,
							PasswordEncoder passwordEncoder,
							JwtTokenProvider jwtTokenProvider) 
	{
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	// login 

	public String login(LoginDto loginDto) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		
		
		return token;
	}
	
	
	
	//register

	@Override
	public String register(ResgisterDto resgisterDto) {
		if(userRepository.existsByusername(resgisterDto.getUsername())) {
			throw new BlogAPIException("User name has alredy exists!", HttpStatus.BAD_REQUEST);
		}
		if(userRepository.existsByEmail(resgisterDto.getEmail())) {
			throw new BlogAPIException("User email has alredy exists!", HttpStatus.BAD_REQUEST);
		}
		
		//save in data base
		
		User user = new User();

		user.setUsername(resgisterDto.getUsername());
		user.setEmail(resgisterDto.getEmail());
		user.setPassword(passwordEncoder.encode(resgisterDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return "user registerted sucsses!";
	    }

	@Override
	public UserDto getUserByEmail(String email) {
		User userEmail = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email", "email", email));
		return this.modelMapper.map(userEmail, UserDto.class);
	}

	
	
	
}
	
