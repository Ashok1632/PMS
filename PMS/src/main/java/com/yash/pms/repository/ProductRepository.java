package com.yash.pms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.pms.entity.Category;
import com.yash.pms.entity.Product;
import com.yash.pms.entity.User;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
List<Product> findByUser(User user);

List<Product> findByCategory(Category category);

@Query("select p from Product p where p.productName like :key")
List<Product> findByProductName(@Param("key") String productName);




}
