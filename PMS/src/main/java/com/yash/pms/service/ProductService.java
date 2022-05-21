package com.yash.pms.service;

import java.util.List;

import com.yash.pms.payload.ProductDto;




public interface ProductService {
	//create Product
 ProductDto addProduct(ProductDto productDto,Integer userId,Integer categoryId);
 
 //update Product
 ProductDto updateProduct(ProductDto productDto,Integer productId);
 //get Product by id
 ProductDto getProductById(Integer productId);
 //delete Product
 void deleteProduct(Integer productId);
 //get all Product
 List<ProductDto> getAllProduct();
 //get Product by category
 List<ProductDto> getProductsByCategory(Integer categoryId);
 
 // get Product by user
 List<ProductDto> getProductsByUser(Integer userId);
 //search Product
 List<ProductDto> searchProducts(String Keyword);
}
