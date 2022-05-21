package com.yash.pms.service;

import java.util.List;

import com.yash.pms.payload.CategoryDto;



public interface CategoryService {
//create
	CategoryDto createCategory (CategoryDto categoyDto);
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	//get category by id
	CategoryDto getcategoryById(Integer categoryId);
	// get all category
	List<CategoryDto> getAllCategory();
	//delete
	void deleteCategory(Integer categoryId);
}
