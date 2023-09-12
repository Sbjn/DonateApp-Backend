package com.donate.Dtofiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
	
	private Integer id;
	
	private String email;
	
	private String username;
	
	private String accessToken;
	
	private String tokenType ="Bearer ";
	
}
