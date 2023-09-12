package com.donate.service;

import java.util.List;

import com.donate.Dtofiles.PostDto;
import com.donate.entity.Post;

public interface PostService {

	// Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// Update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// getPost
	PostDto getPostById(Integer postId);

	// getAll
	List<PostDto> getAllPost();

	// get all posts by user
	List<PostDto> getAllPostByUser(Integer userId);

	// get all posts by category id
	List<PostDto> getAllPostByCategory(Integer categoryId);


	// Search post

	List<Post> searchPosts(String keyword);

}
