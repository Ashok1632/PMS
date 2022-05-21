package com.yash.pms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.pms.config.AppConstants;
import com.yash.pms.payload.ApiResponse;
import com.yash.pms.payload.CategoryDto;
import com.yash.pms.payload.ProductDto;

import com.yash.pms.service.FileService;
import com.yash.pms.service.ProductService;



@RestController
@RequestMapping("/api")
public class ProductController {
	
@Autowired
private ProductService productService;

@Autowired
private FileService fileService;

  @Value("${project.image}")
private String path;

@PostMapping("/user/{userId}/category/{categoryId}/products")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		ProductDto createProduct=this.productService.addProduct(productDto, userId, categoryId);
		return new ResponseEntity<ProductDto>(createProduct,HttpStatus.CREATED);
	}
//get by user
@GetMapping("/user/{userId}/products")
public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable Integer userId)
{
	List<ProductDto> products=this.productService.getProductsByUser(userId);
	return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	
}
//get by category
@GetMapping("/category/{categoryId}/products")
public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Integer categoryId)
{
	List<ProductDto> products=this.productService.getProductsByCategory(categoryId);
	return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	
}

//get all Product
@GetMapping("/products")
public ResponseEntity<List<ProductDto>> getAllProduct()
{
	List<ProductDto> products=this.productService.getAllProduct();
	return ResponseEntity.ok(products);
	
}
//get Product by id
@GetMapping("/products/{productId}")
public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId)
{
	ProductDto productDtos=this.productService.getProductById(productId);
	
	return new ResponseEntity<ProductDto>(productDtos,HttpStatus.OK);
	
}
//delete Product
@DeleteMapping("/products/{productId}")
public ApiResponse deleteProduct(@PathVariable Integer productId)
{
	this.productService.deleteProduct(productId);
	return new ApiResponse("Product sucessfully deleted !!",true);
	
}
//update Product
@PutMapping("/products/{productId}")
ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productdto,@PathVariable Integer productId)
{
	ProductDto updateProduct=this.productService.updateProduct(productdto, productId);
	
	return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
	
}
//search
@GetMapping("/products/search/{keywords}")
ResponseEntity<List<ProductDto>> searchProductByProductName(@PathVariable ("keywords") String keywords)
{
	List<ProductDto> result=this.productService.searchProducts(keywords);
	return new ResponseEntity<List<ProductDto>>(result,HttpStatus.OK);
	
}

//upload image
@PostMapping("/Product/image/upload/{productId}")
public ResponseEntity<ProductDto> uploadProductImage(@RequestParam("image") MultipartFile image,@PathVariable Integer productId) throws IOException
{
	ProductDto productDto=this.productService.getProductById(productId);
	String fileName=this.fileService.uploadImage(path, image);
	
	
	productDto.setImageName(fileName);
	ProductDto updateProduct=this.productService.updateProduct(productDto, productId);
	return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
}

//serve image
@GetMapping(value="product/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response)throws IOException
{
	InputStream resource=this.fileService.getResource(path, imageName);
	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	StreamUtils.copy(resource,response.getOutputStream());
}


}
