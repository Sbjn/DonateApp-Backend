package com.donate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.donate.entity.Roomchat;

public interface RoomchatRepository extends JpaRepository<Roomchat, Integer>{
	
	@Query("Select u from Roomchat u where (u.userFriend = :userName1 and u.userName = :userName2) or (u.userFriend = :userName2 and u.userName = :userName1)")
	public Roomchat findRoomchat(@Param("userName1")String userName1,@Param("userName2") String userName2);

	
}
	
	
	
