package com.donate.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donate.Dtofiles.RoomchatUserDTO;
import com.donate.entity.Roomchat;
import com.donate.repository.RoomchatRepository;
import com.donate.service.RoomchatService;


@Service
public class RoomchatSeriviceImpl implements RoomchatService{
	
	@Autowired
	private RoomchatRepository roomchatRepository;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	//create roomchat
	
	@Override
	public RoomchatUserDTO createRoomchat(RoomchatUserDTO roomchatDto) {
		Roomchat roomchat = this.modelMapper.map(roomchatDto, Roomchat.class);
		roomchat.setUserName(roomchatDto.getUserName());
		roomchat.setUserFriend(roomchatDto.getUserFriend());
		
		Roomchat roomChat = roomchatRepository.save(roomchat);
		
		return this.modelMapper.map(roomChat, RoomchatUserDTO.class);
	}
	
	
	
	//find roomchat by userName 1 and 2
	
	@Override
	public Roomchat findRoomchat(String userName, String userFriend) {
		return roomchatRepository.findRoomchat(userName, userFriend);
	}
	
	
	
	
	
}
