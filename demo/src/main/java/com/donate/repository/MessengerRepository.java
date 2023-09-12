package com.donate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donate.entity.Messenger;
import com.donate.entity.Roomchat;

public interface MessengerRepository extends JpaRepository<Messenger, Integer>{
	
	public List<Messenger> findAllMessageByRoomchat (Roomchat roomchat);
			
}
