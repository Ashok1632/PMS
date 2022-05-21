package com.yash.pms.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.pms.entity.Category;
import com.yash.pms.exception.ResourceNotFoundException;
import com.yash.pms.payload.CategoryDto;
import com.yash.pms.repository.CategoryRepository;
import com.yash.pms.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired 
private CategoryRepository categoryRepository;
@Autowired
private ModelMapper modelmapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat=this.modelmapper.map(categoryDto, Category.class);
		Category addcat=this.categoryRepository.save(cat);
		
		return this.modelmapper.map(addcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Category Id",categoryId));
            cat.setCategoryTitle(categoryDto.getCategoryTitle());
            cat.setCategoryDescription(categoryDto.getCategoryDescription());
             Category updatedcat=this.categoryRepository.save(cat);

		return this.modelmapper.map(updatedcat,CategoryDto.class);
	}

	@Override
	public CategoryDto getcategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "category id",categoryId));
		
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories=this.categoryRepository.findAll();
	List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelmapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "category id",categoryId));
	this.categoryRepository.delete(cat);
	}

}
