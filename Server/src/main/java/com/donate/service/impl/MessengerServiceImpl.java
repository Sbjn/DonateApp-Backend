package com.donate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donate.Dtofiles.MessageDTO;
import com.donate.Exception.ResourceNotFoundException;
import com.donate.entity.Messenger;
import com.donate.entity.Roomchat;
import com.donate.entity.User;
import com.donate.repository.MessengerRepository;
import com.donate.repository.RoomchatRepository;
import com.donate.repository.UserRepository;
import com.donate.service.MessengerService;


@Service
public class MessengerServiceImpl implements MessengerService{
	
	@Autowired
	private MessengerRepository messengerRepository;
	
	@Autowired
	private RoomchatRepository roomchatRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//save message
	@Override
	public MessageDTO saveMessage(MessageDTO messageDTO, Integer userId, Integer roomChatId) {
		Roomchat roomchatId = this.roomchatRepository.findById(roomChatId).orElseThrow(() -> new ResourceNotFoundException("Room chat", "room chat Id", roomChatId));
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		
		Messenger messenger = this.modelMapper.map(messageDTO, Messenger.class);
		messenger.setMessage(messageDTO.getMessage());
		messenger.setRoomchat(roomchatId);
		messenger.setUser(user);
	
		
		
		Messenger saveMess = messengerRepository.save(messenger);
		
		return this.modelMapper.map(saveMess, MessageDTO.class);
	}
	
	
	//get all message by room chat id
	@Override
	public List<MessageDTO> findAllByRoomchatId(Integer roomchatId) {
		Roomchat roomchat = this.roomchatRepository.findById(roomchatId).orElseThrow(() -> new ResourceNotFoundException("Roomchat", "roomchat id", roomchatId));
		List<Messenger> AllMessageByRoomchat = messengerRepository.findAllMessageByRoomchat(roomchat);
		List<MessageDTO> allMessage = AllMessageByRoomchat.stream().map(message -> this.modelMapper.map(message, MessageDTO.class)).collect(Collectors.toList());
		return allMessage;
	}
	
	
	
	
}
