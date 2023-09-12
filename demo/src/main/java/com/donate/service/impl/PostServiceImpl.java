package com.donate.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.donate.Dtofiles.PostDto;
import com.donate.Exception.ResourceNotFoundException;
import com.donate.entity.Category;
import com.donate.entity.Post;
import com.donate.entity.User;
import com.donate.repository.CategoryRepository;
import com.donate.repository.PostRepository;
import com.donate.repository.UserRepository;
import com.donate.service.PostService;


@Service
public class PostServiceImpl implements PostService{
	
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;


	
	

	@Value("project-image")
	String path;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		
		
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepository.save(post);
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImage(postDto.getImage());
		
		Post updatePost = this.postRepository.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		this.postRepository.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post posts = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
		return this.modelMapper.map(posts, PostDto.class);
	}

	

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts = postRepository.findAll();
		List<PostDto> postsDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDtos;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id",userId));
		List<Post> posts = this.postRepository.findAllByUser(user);
		
		List<PostDto> postDto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}
	
	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category cate  = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		List<Post> posts = postRepository.findAllByCategory(cate);
		List<PostDto> postDto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	
	
	@Override
	public List<Post> searchPosts(String keyword) {
	
		return null;
	}

	





	
	
}
