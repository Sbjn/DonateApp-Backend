package com.donate.Dtofiles;

import com.donate.entity.User;

import lombok.Data;

@Data
public class MessageDTO {
	
	private String message;
	private User username;
	
}
