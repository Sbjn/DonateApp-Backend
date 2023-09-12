package com.donate.Dtofiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	private Integer categoryId;
	
	private String categoryTitle;
	
	private String categoryDescription;
}
