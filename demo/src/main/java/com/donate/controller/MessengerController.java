package com.donate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donate.Dtofiles.MessageDTO;
import com.donate.service.MessengerService;

@RestController
@RequestMapping("/api/message")
public class MessengerController {
	
	
	@Autowired
	private MessengerService messengerService;
	
	@PostMapping("/roomchat/{roomChatId}/user/{userId}")
	public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageDTO messageDTO,@PathVariable Integer userId,@PathVariable Integer roomChatId){
		MessageDTO saveMessage = this.messengerService.saveMessage(messageDTO, userId, roomChatId);
		return new ResponseEntity<MessageDTO>(saveMessage, HttpStatus.CREATED);
	}
	
	@GetMapping("/allmessage/{roomchatId}")
	public ResponseEntity<List<MessageDTO>> getAllmessageByRoomChatId (@PathVariable Integer roomchatId){
		List<MessageDTO> findAllByRoomchatId = this.messengerService.findAllByRoomchatId(roomchatId);
		return new ResponseEntity<List<MessageDTO>>(findAllByRoomchatId, HttpStatus.OK);
	}
}
