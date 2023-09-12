package com.donate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donate.Dtofiles.CategoryDto;

import com.donate.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	@PreAuthorize("hashRole('ADMIN')")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{cateId}")
	@PreAuthorize("hashRole('ADMIN')")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer cateId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, cateId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	//delete
	@DeleteMapping("/{cateId}")
	@PreAuthorize("hashRole('ADMIN')")
	public String deleteCategory(@PathVariable Integer cateId){
		this.categoryService.deleteCategory(cateId);
		return "delete sucssesfully!";
	}
	
	//get
	@GetMapping("/{cateId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer cateId){
		CategoryDto categoryDto = this.categoryService.getCategory(cateId);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}
}
