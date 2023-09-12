package com.donate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.donate.Dtofiles.CategoryDto;
import com.donate.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query("Select u from Category u where u.categoryTitle = :categoryTitle")
	public CategoryDto findByCategoryTitle(@Param("categoryTitle") String categoryTitle);
}
