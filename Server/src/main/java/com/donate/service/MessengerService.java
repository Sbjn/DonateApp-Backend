package com.donate.service;

import java.util.List;


import com.donate.Dtofiles.MessageDTO;

public interface MessengerService {
	//save message
	MessageDTO saveMessage (MessageDTO messageDTO, Integer userId, Integer roomChatId);
	//get all message by room chat id
	List<MessageDTO> findAllByRoomchatId(Integer roomchatId);
	
}
