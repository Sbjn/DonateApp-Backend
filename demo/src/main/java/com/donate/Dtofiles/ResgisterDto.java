package com.donate.Dtofiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResgisterDto {
	
	private Integer userId;
	private String username;
	private String email;
	private String password;
}
