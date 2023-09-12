package com.donate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donate.entity.Category;
import com.donate.entity.Post;
import com.donate.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findAllByUser(User user);
	List<Post> findAllByCategory(Category category);
}
