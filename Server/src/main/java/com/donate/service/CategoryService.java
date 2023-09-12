package com.donate.service;

import java.util.List;

import com.donate.Dtofiles.CategoryDto;

public interface CategoryService {
	//Create
	CategoryDto createCategory(CategoryDto categoryDto);
	//Update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	//delete
	void deleteCategory(Integer categoryId);
	//get
	CategoryDto getCategory(Integer categoryId);
	//getAll
	
	List<CategoryDto> getAllCategory();
}
