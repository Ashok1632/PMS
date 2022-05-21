package com.yash.pms.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yash.pms.entity.Category;
import com.yash.pms.entity.Product;
import com.yash.pms.entity.User;
import com.yash.pms.exception.ResourceNotFoundException;
import com.yash.pms.payload.CategoryDto;
import com.yash.pms.payload.ProductDto;

import com.yash.pms.payload.UserDto;
import com.yash.pms.repository.CategoryRepository;
import com.yash.pms.repository.ProductRepository;
import com.yash.pms.repository.UserRepository;
import com.yash.pms.service.ProductService;


@Service
public class ProductsServiceImpl implements ProductService {
@Autowired
private ProductRepository productRepository;
@Autowired
private ModelMapper modelMapper;
@Autowired
private UserRepository userRepository;
@Autowired
private CategoryRepository categoryRepository;

//create Product
	@Override
	public ProductDto addProduct(ProductDto productDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		Category cat=this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Category Id",categoryId));
		Product product=this.modelMapper.map(productDto, Product.class);

		product.setImageName("default.png");
		product.setAddedDate(new Date());
		product.setUser(user);
		product.setCategory(cat);
		
		Product newProduct=this.productRepository.save(product);
		return  this.modelMapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
		// TODO Auto-generated method stub
		Product product=this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("product","product id",productId));
		product.setProductName(productDto.getProductName());
		product.setSerialName(productDto.getSerialName());
		product.setImageName(productDto.getImageName());
		Product updatedProduct=this.productRepository.save(product);
		
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		// TODO Auto-generated method stub
		Product product= this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("product","product id",productId));
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public void deleteProduct(Integer productId) {
		// TODO Auto-generated method stub
		Product product=this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("product","Product id",productId));
		this.productRepository.delete(product);
		
	}

	@Override
	public List<ProductDto> getAllProduct() {
		// TODO Auto-generated method stub
		
		
		
		List<Product> products=this.productRepository.findAll();
		
		List<ProductDto> productDtos=products.stream().map((product)->this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public List<ProductDto> getProductsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
		List<Product> products=this.productRepository.findByCategory(cat);
		List<ProductDto> productDtos=products.stream().map((product)->this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public List<ProductDto> getProductsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
		List<Product> products=this.productRepository.findByUser(user);
		List<ProductDto> productDtos=products.stream().map((product)->this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public List<ProductDto> searchProducts(String Keyword) {
		// TODO Auto-generated method stub
		List<Product> products=this.productRepository.findByProductName("%"+Keyword+"%");
	List<ProductDto> productDtos=products.stream().map((product)->this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}
	

}
