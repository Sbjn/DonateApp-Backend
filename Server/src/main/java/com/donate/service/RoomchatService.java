package com.donate.service;

import com.donate.Dtofiles.RoomchatUserDTO;
import com.donate.entity.Roomchat;

public interface RoomchatService {
	
	//create roomchat
		RoomchatUserDTO createRoomchat(RoomchatUserDTO roomchatDto);
	//find roomchatby userName 1 and 2
		Roomchat findRoomchat(String userName1, String userName2);
		
}	
