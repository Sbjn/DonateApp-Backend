package com.donate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.donate.Dtofiles.Message;

@Controller
@CrossOrigin
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }
}
//	 @Autowired
//	    private SimpMessagingTemplate simpMessagingTemplate;
//
//	    @MessageMapping("/message")
//	    @SendTo("/chatroom/public")
//	    public ChatMessage receiveMessage(@Payload ChatMessage message){
//	        return message;
//	    }
//
//	    @MessageMapping("/private-message")
//	    public ChatMessage recMessage(@Payload ChatMessage message){
//	        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
//	        System.out.println(message.toString());
//	        return message;
//	    }
	
	
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
	
