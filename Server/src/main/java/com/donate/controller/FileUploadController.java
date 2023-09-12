package com.donate.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.donate.Dtofiles.ImageResponseDto;
import com.donate.Dtofiles.PostDto;
import com.donate.service.FileService;
import com.donate.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class FileUploadController {
	
	@Autowired
	private FileService fileService;
	
	
	@Autowired
	private PostService postService;
	
	
	@Value("project-image")
	String path;
	
	
	// upload Image
	
	@PostMapping("/image")
	public ResponseEntity<ImageResponseDto> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException{
		String uploadImage = this.fileService.uploadImage(path, multipartFile);
		
		ImageResponseDto response = new ImageResponseDto();
        response.setFileName(uploadImage);
        response.setSize(multipartFile.getSize());
		
		return new ResponseEntity<ImageResponseDto>(response,HttpStatus.OK);
	}
	
	// get image
		@GetMapping(value = "/profiles/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(@PathVariable ("imageName") String imageName,
				HttpServletResponse response
				) throws IOException {
				InputStream resource = this.fileService.getResource(path, imageName);
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
		}
		
	
	
	
	// Update image by postId
	
	@PutMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> updateImagePostById(
			@RequestParam("image") MultipartFile multipartFile, 
			@PathVariable Integer postId) throws IOException{
		
		String uploadImage = this.fileService.uploadImage(path, multipartFile);
		PostDto post = this.postService.getPostById(postId);
		post.setImage(uploadImage);
		
		PostDto updatePost = this.postService.updatePost(post, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	
	
	
}
