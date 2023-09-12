package com.donate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donate.Dtofiles.ChatMessage;
import com.donate.Dtofiles.MessageDTO;
import com.donate.Dtofiles.UserDto;
import com.donate.entity.Messenger;
import com.donate.entity.Roomchat;
import com.donate.entity.User;
import com.donate.service.MessengerService;
import com.donate.service.UserService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class WebSocketController {
	
	 @Autowired
	    private SimpMessagingTemplate simpMessagingTemplate;

	    @MessageMapping("/message")
	    @SendTo("/chatroom/public")
	    public ChatMessage receiveMessage(@Payload ChatMessage message){
	        return message;
	    }

	    @MessageMapping("/private-message")
	    public ChatMessage recMessage(@Payload ChatMessage message){
	        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
	        System.out.println(message.toString());
	        return message;
	    }
	
	
//	@Autowired
//	private SimpMessagingTemplate simpMessagingTemplate;
//	
//	@Autowired
//	private MessengerService messengerService;
//	
//	@Autowired
//	private UserService userService;
//	Roomchat Roomchat;
//	
//	@MessageMapping("/users")
//	public void greeting (@Payload MessageDTO messageDTO) {
//		User user = userService.findByUserName(messageDTO.getUsername());
//		Messenger messsenger = new Messenger();
//		messsenger.setMessage(messageDTO.getMessage());
//		messsenger.setUser(user);
//		messsenger.setRoomchat(Roomchat);
//		messengerService.saveMessage(messageDTO, user.getId(), Roomchat.getId());
//		simpMessagingTemplate.convertAndSend("/topic/public" + this.Roomchat);
//	}
	
}
