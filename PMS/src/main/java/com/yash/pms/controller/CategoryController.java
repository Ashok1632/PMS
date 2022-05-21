package com.yash.pms.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.yash.pms.payload.ApiResponse;
import com.yash.pms.payload.CategoryDto;
import com.yash.pms.service.CategoryService;



@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createUser(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategorydto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategorydto, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable  Integer catId)
	{
		CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto,catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable  Integer catId)
	{
	this.categoryService.deleteCategory(catId);
	//without use ApiResponse
	//return new ResponseEntity(Map.of("message","user deleted successfully"), HttpStatus.OK);
	return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted sucessfully",true),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getALLCategories()
	{
		List<CategoryDto> categories=this.categoryService.getAllCategory();
		return ResponseEntity.ok(categories);
		
	}
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getByCategoryId(@PathVariable  Integer catId)
	{
		CategoryDto categoryDto=this.categoryService.getcategoryById(catId);
	return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);

	}
}

