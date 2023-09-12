package com.donate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donate.Dtofiles.RoomchatUserDTO;
import com.donate.entity.Roomchat;
import com.donate.service.RoomchatService;

@RestController
@RequestMapping("/api/roomchat")
public class RoomchatController {
	
	@Autowired
	private RoomchatService roomchatService;
	
	@PostMapping("/add")
	public ResponseEntity<RoomchatUserDTO> createRoomchat(@RequestBody RoomchatUserDTO roomchatUserDTO){
		RoomchatUserDTO createRoomchat = roomchatService.createRoomchat(roomchatUserDTO);
		return new ResponseEntity<RoomchatUserDTO>(createRoomchat,HttpStatus.CREATED);
	}
	
	@GetMapping("/findroomchat/{userName1}/{userName2}")
	public Integer findRoomChatid(@PathVariable String userName1, @PathVariable String userName2){
		Roomchat findRoomchat = roomchatService.findRoomchat(userName1, userName2);
		return findRoomchat.getId();
	}
	
}
