package com.donate.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donate.Dtofiles.PostDto;
import com.donate.Exception.APIResponse;
import com.donate.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	
	
	//create 
		@PostMapping("/category/{categoryId}/user/{userId}/posts")
		public ResponseEntity<PostDto> createPost(
				@RequestBody PostDto postDto, 
				@PathVariable Integer userId, 
				@PathVariable Integer categoryId
				) {
				
			PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
			
			return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
			
		}
	
	//update Post
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
			PostDto updatePost = this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		}
		
	//delete Post
		@DeleteMapping("/posts/{postId}")
		public APIResponse deletePost (@PathVariable Integer postId){
			this.postService.deletePost(postId);
			return new APIResponse("Post is sucssesfully deleted",true);
		}
		
	
	//get By user
		@GetMapping("/user/{userId}/posts")
		public ResponseEntity<List<PostDto>> getPostByUser (@PathVariable Integer userId){
			List<PostDto> allPostByUser = this.postService.getAllPostByUser(userId);
			return new ResponseEntity<List<PostDto>>(allPostByUser,HttpStatus.OK);
		}
		
	//get By category id
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostByCategory (@PathVariable Integer categoryId){
			List<PostDto> allPostByCategory = this.postService.getAllPostByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(allPostByCategory, HttpStatus.OK);
		}
			

	//get all posts
		@GetMapping("/posts")
		public ResponseEntity<List<PostDto>> getAllPost(){
			List<PostDto> allPosts = this.postService.getAllPost();
			return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
		}
		
	//get post by id
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
			PostDto postById = this.postService.getPostById(postId);
			return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
		}
		
}
